package org.money.utilisateurmicroservice.servicesImp;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.money.utilisateurmicroservice.config.KeycloakConfig;
import org.money.utilisateurmicroservice.dtos.LoginDto;
import org.money.utilisateurmicroservice.dtos.UserKeycloakRequestDto;
import org.money.utilisateurmicroservice.services.KeycloakService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    private final KeycloakConfig keycloakUtil;

    /**
     * Create user in Keycloak with given user request
     *
     * @param userRequest
     * @return user ID
     */
    @Override
    public String createUserInKeycloak(UserKeycloakRequestDto userRequest) {
        log.info("Creating user in Keycloak: {}", userRequest.getUsername());

        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        UserRepresentation user = buildUserRepresentation(userRequest);

        javax.ws.rs.core.Response response = usersResource.create(user);
        if (response.getStatus() != 201) {
            log.error("Failed to create user in Keycloak");
            throw new RuntimeException("Keycloak user creation failed");
        }

        String userId = CreatedResponseUtil.getCreatedId(response);
        assignRoleAndGroupToUser(userId, userRequest.getRole());
        log.info("User created successfully in Keycloak with ID: {}", userId);
        return userId;
    }

    /**
     * Get user from Keycloak by username
     *
     * @param userRequest
     * @param userId
     */
    @Override
    public void updateUserInKeycloak(UserKeycloakRequestDto userRequest, String userId) {
        log.info("Updating user in Keycloak: {}", userId);

        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        UserRepresentation user = usersResource.get(userId).toRepresentation();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setEnabled(userRequest.getEnabled());
        user.setEmailVerified(false);

        try {
            usersResource.get(userId).update(user);
            assignRoleAndGroupToUser(userId, userRequest.getRole());
            log.info("User updated successfully in Keycloak with ID: {}", userId);
        } catch (Exception e) {
            log.error("Failed to update user in Keycloak", e);
            throw new RuntimeException("Keycloak user update failed", e);
        }
    }

    /**
     * Build user representation
     *
     * @param userRequest
     * @return
     */
    private UserRepresentation buildUserRepresentation(UserKeycloakRequestDto userRequest) {
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(userRequest.getPassword());
        passwordCredential.setTemporary(false);

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setCredentials(Arrays.asList(passwordCredential));

        user.setEnabled(userRequest.getEnabled());
        user.setEmailVerified(false);

        return user;
    }

    /**
     * Assign role to user and delete all other roles
     *
     * @param userId
     * @param role
     */
    private void assignRoleAndGroupToUser(String userId, String role) {
        // Get UserResource
        UserResource userResource = keycloakUtil.getRealmResource().users().get(userId);

        // Get all realm-level roles of the user
        List<RoleRepresentation> userRoles = userResource.roles().realmLevel().listAll();

        // Remove all roles
        userResource.roles().realmLevel().remove(userRoles);

        // Add new role
        RoleRepresentation roleRepresentationToAdd = keycloakUtil.getRealmResource().roles().get(role).toRepresentation();
        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentationToAdd));

    }

    @Override
    public void deleteUserInKeycloak(String userId) {
        log.info("Deleting user in Keycloak: {}", userId);

        UsersResource usersResource = keycloakUtil.getRealmResource().users();
        Response response = usersResource.delete(userId);

        if (response.getStatus() != 204) {
            log.error("Failed to delete user in Keycloak");
            throw new RuntimeException("Keycloak user deletion failed");
        }

        log.info("User deleted successfully in Keycloak with ID: {}", userId);
    }

    // get the access token from the keycloak
    @Override
    public String getToken(LoginDto loginDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("username", loginDTO.getNomUtilisateur());
        map.add("password", loginDTO.getPassword());
        map.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        String tokenUrl = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    tokenUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // Handle 401 Unauthorized response
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
            }
            throw e;
        }
    }
}

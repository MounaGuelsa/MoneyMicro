package org.money.utilisateurmicroservice.services;

import org.keycloak.representations.AccessTokenResponse;
import org.money.utilisateurmicroservice.dtos.KeycloakUser;
import org.money.utilisateurmicroservice.dtos.LoginDto;
import org.money.utilisateurmicroservice.dtos.LoginRequest;
import org.money.utilisateurmicroservice.dtos.UserKeycloakRequestDto;


public interface KeycloakService {
    String createUserInKeycloak(UserKeycloakRequestDto userRequest);

    void updateUserInKeycloak(UserKeycloakRequestDto userRequest, String userId);

    void deleteUserInKeycloak(String userId);

    // get the access token from the keycloak
    String getToken(LoginDto loginDTO);
}

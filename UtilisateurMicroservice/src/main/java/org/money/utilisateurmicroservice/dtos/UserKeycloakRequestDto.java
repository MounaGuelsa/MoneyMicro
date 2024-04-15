package org.money.utilisateurmicroservice.dtos;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for keycloak user creation
 */
@Data
@Builder
public class UserKeycloakRequestDto {

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Role is required")
    private String role;

    private Boolean enabled;
}
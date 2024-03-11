package org.money.utilisateurmicroservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.money.utilisateurmicroservice.entities.Role;

import java.io.Serializable;

/**
 * DTO for {@link org.money.utilisateurmicroservice.entities.Utilisateur}
 */
@Value
public class UtilisateurDto implements Serializable {
    @NotNull
    Long id_Utilisateur;
    @NotBlank
    String nom;
    @NotBlank
    String prenom;
    @Email
    String email;
    @NotBlank
    String password;
    @NotNull
    Role role;
}
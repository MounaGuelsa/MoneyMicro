package org.money.utilisateurmicroservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.money.utilisateurmicroservice.entities.Role;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurDto {
    private Long id_Utilisateur;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String nom_utilisateur;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
    private String idKeycloak;

}
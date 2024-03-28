package org.money.feignclient.utilisateur;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Role role;


}
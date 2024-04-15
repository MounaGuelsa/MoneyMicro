package org.money.utilisateurmicroservice.dtos;

import lombok.Data;
import org.money.utilisateurmicroservice.entities.Role;

@Data
public class KeycloakUser {
    private Long id_Utilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;

}

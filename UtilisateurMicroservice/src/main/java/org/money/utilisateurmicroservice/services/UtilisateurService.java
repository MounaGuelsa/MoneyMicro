package org.money.utilisateurmicroservice.services;

import org.money.utilisateurmicroservice.dtos.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto);
    UtilisateurDto obtenirUtilisateurParId(Long id);
    UtilisateurDto modifierUtilisateur(Long id, UtilisateurDto utilisateurDto);
    void supprimerUtilisateur(Long id);
    List<UtilisateurDto> obtenirUtilisateurs();

}

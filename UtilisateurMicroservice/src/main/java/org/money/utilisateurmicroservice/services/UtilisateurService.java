package org.money.utilisateurmicroservice.services;

import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UtilisateurService {

    UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto);
    UtilisateurDto obtenirUtilisateurParId(Long id);
    UtilisateurDto modifierUtilisateur(Long id, UtilisateurDto utilisateurDto);
    void supprimerUtilisateur(Long id);
    Page<UtilisateurDto> obtenirUtilisateurs(Pageable pageable);

}

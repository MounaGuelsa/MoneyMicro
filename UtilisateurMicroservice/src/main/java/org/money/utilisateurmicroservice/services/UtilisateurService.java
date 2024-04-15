package org.money.utilisateurmicroservice.services;

import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto creerUtilisateur(UtilisateurDto utilisateurDto);
    UtilisateurDto obtenirUtilisateurParId(Long id);
    void SupprimerUtilisateur(Long id);

}
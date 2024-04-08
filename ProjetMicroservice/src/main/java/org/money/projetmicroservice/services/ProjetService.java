package org.money.projetmicroservice.services;

import org.money.projetmicroservice.dtos.request.ProjetDepenseRequestDto;

import java.util.List;

public interface ProjetService {
//    public List<ProjetDto> obtenirProjets();
//    ProjetDto obtenirProjetParId(Long id);
//    ProjetDto ajouterProjet(ProjetDto projetDto);
//    ProjetDto modifierProjet(Long id, ProjetDto projetDto);
//    void supprimerProjet(Long id);
//


    void ajouterProjetAvecDepenses(ProjetDepenseRequestDto projetDepenseRequestDto);
}

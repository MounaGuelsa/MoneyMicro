package org.money.depensemicroservice.services;


import org.money.depensemicroservice.dtos.DepenseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DepenseService {
    List<DepenseDto> obtenirDepenses();

    DepenseDto obtenirDepenseParId(Long id);

    DepenseDto ajouterDepense(DepenseDto depenseDTO);

    DepenseDto modifierDepense(Long id, DepenseDto depenseDTO);

    void supprimerDepense(Long id);

    List<DepenseDto> obtenirDepensesParUtilisateur(Long utilisateurId);


    List<DepenseDto> obtenirDepensesEntreDates(LocalDate debut, LocalDate fin);
    Double totalDepensesParMois();

}

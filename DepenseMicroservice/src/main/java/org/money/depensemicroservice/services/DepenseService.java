package org.money.depensemicroservice.services;


import org.money.depensemicroservice.dtos.DepenseDto;

import java.util.List;

public interface DepenseService {
    List<DepenseDto> obtenirDepenses();

    DepenseDto obtenirDepenseParId(Long id);

    DepenseDto ajouterDepense(DepenseDto depenseDTO);

    DepenseDto modifierDepense(Long id, DepenseDto depenseDTO);

    void supprimerDepense(Long id);

    Double totalDepensesParMois();

}

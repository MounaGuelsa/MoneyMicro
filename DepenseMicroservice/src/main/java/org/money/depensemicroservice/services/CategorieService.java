package org.money.depensemicroservice.services;

import org.money.depensemicroservice.dtos.CategorieDto;

import java.util.List;

public interface CategorieService {
    List<CategorieDto> obtenirCategories();
    CategorieDto obtenirCategorieParId(Long id);
    CategorieDto ajouterCategorie(CategorieDto categorieDTO);
    CategorieDto modifierCategorie(Long id, CategorieDto categorieDTO);
    void supprimerCategorie(Long id);
}

package org.money.depensemicroservice.services;

import org.money.depensemicroservice.dtos.FactureDto;

import java.util.List;

public interface FactureService {
    public List<FactureDto> obtenirFactures();
    public FactureDto obtenirFactureParId(Long id);
    public FactureDto ajouterFacture(FactureDto factureDTO);
    public FactureDto modifierFacture(Long id, FactureDto factureDTO);
    public void supprimerFacture(Long id);}
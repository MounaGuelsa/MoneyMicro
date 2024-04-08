package org.money.revenuemicroservice.services;

import org.money.revenuemicroservice.dtos.RevenueDto;

import java.util.List;

public interface RevenueService {
    public List<RevenueDto> obtenirRevenues();
    RevenueDto obtenirRevenueParId(Long id);
    RevenueDto ajouterRevenue(RevenueDto revenueDto);
    RevenueDto modifierRevenue(Long id, RevenueDto revenueDto);
    void supprimerRevenue(Long id);

    List<RevenueDto> rechercherRevenuesParNom(String nom);

    Double totalRevenuesParMois();
}

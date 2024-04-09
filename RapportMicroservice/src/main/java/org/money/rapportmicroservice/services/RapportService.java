package org.money.rapportmicroservice.services;

import org.money.rapportmicroservice.dtos.RapportDto;
import org.money.rapportmicroservice.dtos.StatistiqueDto;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface RapportService {


    StatistiqueDto obtenirStatistiques();

    @Scheduled(cron = "0 0 0 1 * ?")
    void generateRapports();

    List<RapportDto> obtenirRapports();

    List<RapportDto> obtenirRapportsParMois(int mois, int annee);

    List<RapportDto> obtenirListeRapports();

    void supprimerRapport(Long id);
}

package org.money.rapportmicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.feignclient.Depense.DepenseClient;
import org.money.feignclient.Revenue.RevenueClient;
import org.money.rapportmicroservice.dtos.RapportDto;
import org.money.rapportmicroservice.dtos.StatistiqueDto;
import org.money.rapportmicroservice.entities.Rapport;
import org.money.rapportmicroservice.exceptions.CustomException;
import org.money.rapportmicroservice.mappers.RapportMapper;
import org.money.rapportmicroservice.repositories.RapportRepository;
import org.money.rapportmicroservice.services.RapporService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RapportServiceImp implements RapporService {
    private RapportMapper rapportMapper;
    private final RapportRepository rapportRepository;
    private final DepenseClient depenseClient;
    private final RevenueClient revenueClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(RapportServiceImp.class);

    @Override
    public Map<String, Double> genererRapportMensuel(String titre, String moisAnnee) {
        return null;
    }
    @Override
    public List<RapportDto> rechercherRapport() {
        try {
            // Obtenir la date actuelle
            LocalDate dateActuelle = LocalDate.now();

            // Récupérer les rapports jusqu'à la date actuelle
            List<Rapport> rapports = rapportRepository.findByMoisAnneeBefore(dateActuelle.plusDays(1)); // Ajoute 1 jour pour inclure la date actuelle

            // Convertir les rapports en DTOs
            return rapports.stream().map(rapportMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la recherche du rapport : {}", e.getMessage());
            throw new CustomException("Erreur lors de la recherche du rapport", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<RapportDto> getAllRapports() {
        try {
            return rapportRepository.findAll().stream().map(rapportMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération des rapports : {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des rapports", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public Double getDepensesDuMoisActuel() {
        return depenseClient.totalDepensesParMois();
    }
    @Override
    public Double getRevenuesDuMoisActuel() {
        return revenueClient.totalRevenuesParMois();
    }
    @Override
    public StatistiqueDto obtenirStatistiques() {
        Double depenses = depenseClient.totalDepensesParMois();
        Double revenus = revenueClient.totalRevenuesParMois();
        Double balance = revenus - depenses;

        return new StatistiqueDto(depenses, revenus, balance);
    }
}

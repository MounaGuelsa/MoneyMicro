package org.money.rapportmicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.depensemicroservice.exceptions.CustomException;


import org.money.rapportmicroservice.Depense.DepenseClient;
import org.money.rapportmicroservice.Revenue.RevenueClient;
import org.money.rapportmicroservice.dtos.RapportDto;
import org.money.rapportmicroservice.dtos.StatistiqueDto;
import org.money.rapportmicroservice.entities.Rapport;
import org.money.rapportmicroservice.mappers.RapportMapper;
import org.money.rapportmicroservice.repositories.RapportRepository;
import org.money.rapportmicroservice.services.RapportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RapportServiceImp implements RapportService {

    private final RapportRepository rapportRepository;
    private final DepenseClient depenseClient;
    private final RevenueClient revenueClient;
    private  final RapportMapper rapportMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(RapportServiceImp.class);


    @Scheduled(cron = "0 52 17 14 4 ?")
    public void generateRapports() {
        Double depenses = depenseClient.totalDepensesParMois();
        Double revenus = revenueClient.totalRevenuesParMois();
        Double balance = revenus - depenses;

        Rapport rapport = new Rapport();
        rapport.setMoisAnnee(LocalDate.now().withDayOfMonth(1));
        rapport.setDepenses(depenses);
        rapport.setRevenus(revenus);
        rapport.setBalance(balance);
        rapportRepository.save(rapport);
    }
    @Override
    public StatistiqueDto obtenirStatistiques() {
        Double depenses = depenseClient.totalDepensesParMois();
        Double revenus = revenueClient.totalRevenuesParMois();
        Double balance = revenus - depenses;
        return new StatistiqueDto(depenses, revenus, balance);
    }
    @Override
    public List<RapportDto> obtenirRapports() {
        try {
            List<Rapport> rapports = rapportRepository.findAll();

            return rapports.stream()
                    .map(rapportMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des rapports : {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des rapports", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public List<RapportDto> obtenirRapportsParMois(int mois, int annee) {
        LocalDate dateDebut = LocalDate.of(annee, mois, 1);
        LocalDate dateFin = dateDebut.plusMonths(1).minusDays(1);
        List<Rapport> rapports = rapportRepository.findByMoisAnneeBetween(dateDebut, dateFin);
        return rapports.stream()
                .map(rapportMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<RapportDto> obtenirListeRapports() {
        List<Rapport> rapports = rapportRepository.findAll();
        return rapports.stream()
                .map(rapportMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void supprimerRapport(Long id) {
        rapportRepository.deleteById(id);
    }

}

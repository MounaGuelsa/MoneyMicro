package org.money.rapportmicroservice.controllers;

import lombok.RequiredArgsConstructor;
import org.money.feignclient.Depense.DepenseClient;
import org.money.feignclient.Revenue.RevenueClient;
import org.money.rapportmicroservice.dtos.StatistiqueDto;
import org.money.rapportmicroservice.entities.Rapport;
import org.money.rapportmicroservice.services.RapporService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor

@RequestMapping("/rapports")
public class RapportController {

    private final DepenseClient depenseClient;
    private final RevenueClient revenueClient;
    private final RapporService rapportService;

    @GetMapping("/depensesMoisActuel")
    public ResponseEntity<Double> getDepensesDuMoisActuel() {
        Double total = depenseClient.totalDepensesParMois();
        return ResponseEntity.ok(total);
    }

    @GetMapping("/revenuesMoisActuel")
    public ResponseEntity<Double> getRevenuesDuMoisActuel() {
        Double total = revenueClient.totalRevenuesParMois();
        return ResponseEntity.ok(total);
    }
    @GetMapping("/statistics")
    public ResponseEntity<StatistiqueDto> getFinanceStatistics() {
        StatistiqueDto statistics = rapportService.obtenirStatistiques();
        return ResponseEntity.ok(statistics);
    }
}

package org.money.rapportmicroservice.controllers;

import lombok.RequiredArgsConstructor;
import org.money.rapportmicroservice.dtos.RapportDto;
import org.money.rapportmicroservice.dtos.StatistiqueDto;
import org.money.rapportmicroservice.services.RapportService;
import org.money.rapportmicroservice.servicesImp.RapportServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/rapports")
public class RapportController {

    private final RapportService rapportService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RapportServiceImp.class);
    @GetMapping("/statistics")
    public ResponseEntity<StatistiqueDto> obtenirStatistics() {
        StatistiqueDto statistics = rapportService.obtenirStatistiques();
        return ResponseEntity.ok(statistics);
    }
    @GetMapping("/generate")
    public ResponseEntity<Void> generateRapports() {
        rapportService.generateRapports();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/liste")
    public ResponseEntity<List<RapportDto>> obtenirListeRapports() {
        try {
            List<RapportDto> rapports = rapportService.obtenirListeRapports();
            return new ResponseEntity<>(rapports, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération de la liste des rapports : {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerRapport(@PathVariable Long id) {
        try {
            rapportService.supprimerRapport(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la suppression du rapport : {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/parMois/{mois}/{annee}")
    public ResponseEntity<List<RapportDto>> obtenirRapportsParMois(@PathVariable int mois, @PathVariable int annee) {
        try {
            List<RapportDto> rapports = rapportService.obtenirRapportsParMois(mois, annee);
            return new ResponseEntity<>(rapports, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des rapports par mois : {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

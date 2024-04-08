package org.money.revenuemicroservice.controllers;

import org.money.revenuemicroservice.dtos.RevenueDto;
import org.money.revenuemicroservice.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/revenues")
public class RevenueController {

    private final RevenueService revenueService;

    @Autowired
    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping
    public ResponseEntity<List<RevenueDto>> obtenirRevenues() {
        try {
            List<RevenueDto> revenues = revenueService.obtenirRevenues();
            return new ResponseEntity<>(revenues, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RevenueDto> obtenirRevenueParId(@PathVariable Long id) {
        try {
            RevenueDto revenue = revenueService.obtenirRevenueParId(id);
            if (revenue != null) {
                return new ResponseEntity<>(revenue, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<RevenueDto> ajouterRevenue(@RequestBody RevenueDto revenueDto) {
        try {
            RevenueDto revenue = revenueService.ajouterRevenue(revenueDto);
            return new ResponseEntity<>(revenue, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RevenueDto> modifierRevenue(@PathVariable Long id, @RequestBody RevenueDto revenueDto) {
        try {
            RevenueDto revenue = revenueService.modifierRevenue(id, revenueDto);
            if (revenue != null) {
                return new ResponseEntity<>(revenue, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerRevenue(@PathVariable Long id) {
        try {
            revenueService.supprimerRevenue(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/rechercher")
    public ResponseEntity<List<RevenueDto>> rechercherRevenuesParNom(@RequestParam(required = false) String nom) {
        try {
            List<RevenueDto> revenus = revenueService.rechercherRevenuesParNom(nom);
            return new ResponseEntity<>(revenus, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/total")
    public ResponseEntity<Double> totalRevenuesParMois() {
        Double totalParMois = revenueService.totalRevenuesParMois();
        return new ResponseEntity<>(totalParMois, HttpStatus.OK);
    }
}

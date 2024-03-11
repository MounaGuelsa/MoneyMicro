package org.money.depensemicroservice.controllers;


import org.money.depensemicroservice.dtos.DepenseDto;
import org.money.depensemicroservice.services.DepenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/depenses")
public class DepenseController {

    private final DepenseService depenseService;
    @Autowired

    public DepenseController(DepenseService depenseService) {
        this.depenseService = depenseService;
    }

    @GetMapping
    public ResponseEntity<List<DepenseDto>> obtenirDepenses() {
        List<DepenseDto> depenses = depenseService.obtenirDepenses();
        return ResponseEntity.ok(depenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepenseDto> obtenirDepenseParId(@PathVariable Long id) {
        DepenseDto depense = depenseService.obtenirDepenseParId(id);
        if (depense != null) {
            return ResponseEntity.ok(depense);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DepenseDto> ajouterDepense(@RequestBody DepenseDto depenseDto) {
        DepenseDto depense = depenseService.ajouterDepense(depenseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(depense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepenseDto> modifierDepense(@PathVariable Long id, @RequestBody DepenseDto depenseDto) {
        DepenseDto depense = depenseService.modifierDepense(id, depenseDto);
        if (depense != null) {
            return ResponseEntity.ok(depense);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDepense(@PathVariable Long id) {
        depenseService.supprimerDepense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<DepenseDto>> obtenirDepensesParUtilisateur(@PathVariable Long utilisateurId) {
        List<DepenseDto> depenses = depenseService.obtenirDepensesParUtilisateur(utilisateurId);
        return ResponseEntity.ok(depenses);
    }

    @GetMapping("/entreDates")
    public ResponseEntity<List<DepenseDto>> obtenirDepensesEntreDates(@RequestParam("debut") LocalDate debut, @RequestParam("fin") LocalDate fin) {
        List<DepenseDto> depenses = depenseService.obtenirDepensesEntreDates(debut, fin);
        return ResponseEntity.ok(depenses);
    }
}

package org.money.projetmicroservice.controllers;

import org.money.projetmicroservice.dtos.ProjetDto;
import org.money.projetmicroservice.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
public class ProjetController {

    private final ProjetService projetService;

    @Autowired
    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping
    public ResponseEntity<List<ProjetDto>> obtenirProjets() {
        try {
            List<ProjetDto> projets = projetService.obtenirProjets();
            return new ResponseEntity<>(projets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDto> obtenirProjetParId(@PathVariable Long id) {
        try {
            ProjetDto projet = projetService.obtenirProjetParId(id);
            if (projet != null) {
                return new ResponseEntity<>(projet, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ProjetDto> ajouterProjet(@RequestBody ProjetDto projetDto) {
        try {
            ProjetDto projet = projetService.ajouterProjet(projetDto);
            return new ResponseEntity<>(projet, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetDto> modifierProjet(@PathVariable Long id, @RequestBody ProjetDto projetDto) {
        try {
            ProjetDto projet = projetService.modifierProjet(id, projetDto);
            if (projet != null) {
                return new ResponseEntity<>(projet, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProjet(@PathVariable Long id) {
        try {
            projetService.supprimerProjet(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

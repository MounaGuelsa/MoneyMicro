package org.money.utilisateurmicroservice.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.money.utilisateurmicroservice.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private static final Logger LOGGER = LogManager.getLogger(UtilisateurController.class);

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<Page<UtilisateurDto>> getAllUtilisateurs(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        try {
            Sort sort = Sort.by("nom").ascending(); // Changer le champ utilisé pour trier si nécessaire
            Pageable pageable = PageRequest.of(page, size, sort);

            Page<UtilisateurDto> utilisateursPage = utilisateurService.obtenirUtilisateurs(pageable);

            if (!utilisateursPage.isEmpty()) {
                return new ResponseEntity<>(utilisateursPage, HttpStatus.OK);
            } else {
                LOGGER.warn("No utilisateurs found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching all utilisateurs", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable Long id) {
        try {
            UtilisateurDto utilisateurDto = utilisateurService.obtenirUtilisateurParId(id);
            if (utilisateurDto != null) {
                return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
            } else {
                LOGGER.warn("Utilisateur with ID {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching utilisateur with ID " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        try {
            UtilisateurDto createdUtilisateur = utilisateurService.ajouterUtilisateur(utilisateurDto);
            if (createdUtilisateur != null) {
                return new ResponseEntity<>(createdUtilisateur, HttpStatus.CREATED);
            } else {
                LOGGER.error("Failed to create utilisateur");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while creating the utilisateur", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDto utilisateurDto) {
        try {
            UtilisateurDto updatedUtilisateur = utilisateurService.modifierUtilisateur(id, utilisateurDto);
            if (updatedUtilisateur != null) {
                return new ResponseEntity<>(updatedUtilisateur, HttpStatus.OK);
            } else {
                LOGGER.warn("Utilisateur with ID {} not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while updating utilisateur with ID " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateurById(@PathVariable Long id) {
        try {
            utilisateurService.supprimerUtilisateur(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.error("An error occurred while deleting the utilisateur with ID " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

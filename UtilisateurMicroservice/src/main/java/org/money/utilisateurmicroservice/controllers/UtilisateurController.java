package org.money.utilisateurmicroservice.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.money.utilisateurmicroservice.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private   UtilisateurService utilisateurService;
    private static final Logger LOGGER = LogManager.getLogger(UtilisateurController.class);

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> obtenirTousLesUtilisateurs() {
        try {
            List<UtilisateurDto> utilisateurs = utilisateurService.obtenirUtilisateurs();

            if (!utilisateurs.isEmpty()) {
                return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
            } else {
                LOGGER.warn("Aucun utilisateur trouvé");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération de tous les utilisateurs", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> obtenirUtilisateurParId(@PathVariable Long id) {
        try {
            UtilisateurDto utilisateurDto = utilisateurService.obtenirUtilisateurParId(id);
            if (utilisateurDto != null) {
                return new ResponseEntity<>(utilisateurDto, HttpStatus.OK);
            } else {
                LOGGER.warn("Utilisateur avec l'identifiant {} non trouvé", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération de l'utilisateur avec l'identifiant " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> creerUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        try {
            UtilisateurDto utilisateurCree = utilisateurService.ajouterUtilisateur(utilisateurDto);
            if (utilisateurCree != null) {
                return new ResponseEntity<>(utilisateurCree, HttpStatus.CREATED);
            } else {
                LOGGER.error("Impossible de créer l'utilisateur");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la création de l'utilisateur", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> mettreAJourUtilisateur(@PathVariable Long id, @RequestBody UtilisateurDto utilisateurDto) {
        try {
            UtilisateurDto utilisateurMisAJour = utilisateurService.modifierUtilisateur(id, utilisateurDto);
            if (utilisateurMisAJour != null) {
                return new ResponseEntity<>(utilisateurMisAJour, HttpStatus.OK);
            } else {
                LOGGER.warn("Utilisateur avec l'identifiant {} non trouvé", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la mise à jour de l'utilisateur avec l'identifiant " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateurParId(@PathVariable Long id) {
        try {
            utilisateurService.supprimerUtilisateur(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la suppression de l'utilisateur avec l'identifiant " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

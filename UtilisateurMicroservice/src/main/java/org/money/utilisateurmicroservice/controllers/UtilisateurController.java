package org.money.utilisateurmicroservice.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.money.utilisateurmicroservice.dtos.LoginDto;
import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.money.utilisateurmicroservice.services.KeycloakService;
import org.money.utilisateurmicroservice.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final KeycloakService keycloakService;


    @PostMapping
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto createdUtilisateur = utilisateurService.creerUtilisateur(utilisateurDto);
        return ResponseEntity.ok(createdUtilisateur);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateur(@PathVariable Long id) {
        UtilisateurDto utilisateurDto = utilisateurService.obtenirUtilisateurParId(id);
        return ResponseEntity.ok(utilisateurDto);
    }
    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestBody LoginDto loginDto) {
        String token = keycloakService.getToken(loginDto);
        return ResponseEntity.ok(token);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerUtilisateur(@PathVariable Long id) {
        try {
            utilisateurService.SupprimerUtilisateur(id);
            return ResponseEntity.ok("Utilisateur supprimé avec succès");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }
    }
}
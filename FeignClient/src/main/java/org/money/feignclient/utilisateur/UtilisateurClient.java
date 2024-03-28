package org.money.feignclient.utilisateur;

import jakarta.validation.Valid;
import org.money.feignclient.depense.DepenseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("UTILISATEUR")
public interface UtilisateurClient {
    @PostMapping("/utilisateurs")
    ResponseEntity<UtilisateurDto> ajouterUtilisateur(@RequestBody @Valid UtilisateurDto utilisatuerDto);


    @GetMapping("/utilisateurs/{id}")
    ResponseEntity<UtilisateurDto> obtenirUtilisateurParId(@PathVariable Long id);
}

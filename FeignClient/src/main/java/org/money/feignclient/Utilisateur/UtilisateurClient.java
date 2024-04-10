package org.money.feignclient.Utilisateur;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@FeignClient("Utilisateur")

public interface UtilisateurClient {
    @GetMapping("/utilisateurs")
    List<UtilisateurDto> listeUtilisateurs() ;

    }

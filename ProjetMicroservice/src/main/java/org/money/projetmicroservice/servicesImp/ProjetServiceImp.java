package org.money.projetmicroservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.money.feignclient.Depense.DepenseClient;
import org.money.feignclient.Depense.DepenseDto;
import org.money.projetmicroservice.dtos.request.ProjetDepenseRequestDto;
import org.money.projetmicroservice.repositories.ProjetRepository;
import org.money.projetmicroservice.services.ProjetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjetServiceImp implements ProjetService {

    private final ProjetRepository projetRepository;
    private final DepenseClient depenseClient;

    @Override
    public void ajouterProjetAvecDepenses(ProjetDepenseRequestDto projetDepenseRequestDto) {
//        log.info("Ajouter nouveau projet avec dépenses");
//
//        List<ProjetDepenseRequestDto.DepenseDto> depenses = projetDepenseRequestDto.getDepenses();
//        if (depenses != null) {
//            for (ProjetDepenseRequestDto.DepenseDto depense : depenses) {
//
//                ResponseEntity<DepenseDto> response = depenseClient.ajouterDepense(DepenseDto);
//                if (response.getStatusCode() == HttpStatus.OK) {
//                    DepenseDto addedDepenseDto = response.getBody();
//                    log.info("Depense ajoutée: {}", addedDepenseDto);
//                } else {
//                    log.error("Failed to add depense: {}", response.getStatusCode());
//                }
//            }
//        }
    }


}

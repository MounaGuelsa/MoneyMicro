package org.money.utilisateurmicroservice.servicesImp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.money.utilisateurmicroservice.dtos.UserKeycloakRequestDto;
import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.money.utilisateurmicroservice.entities.Utilisateur;
import org.money.utilisateurmicroservice.mappers.UtilisateurMapper;
import org.money.utilisateurmicroservice.repositories.UtilisateurRepository;
import org.money.utilisateurmicroservice.services.KeycloakService;
import org.money.utilisateurmicroservice.services.UtilisateurService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImp implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final KeycloakService keycloakService;

    @Override
    public UtilisateurDto creerUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);

        // Create user in keycloak
        String keycloakId = keycloakService.createUserInKeycloak(buildKeycloakUser(utilisateurDto));

        // Save user in database
        utilisateur.setIdKeycloak(keycloakId);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDTO(savedUtilisateur);
    }

    private UserKeycloakRequestDto buildKeycloakUser(UtilisateurDto utilisateurDto) {

        return UserKeycloakRequestDto.builder()
                .username(utilisateurDto.getNom_utilisateur())
                .firstName(utilisateurDto.getPrenom())
                .lastName(utilisateurDto.getNom())
                .email(utilisateurDto.getEmail())
                .password(utilisateurDto.getPassword())
                .role(utilisateurDto.getRole().name())
                .enabled(true)
                .build();
    }

    @Override
    public UtilisateurDto obtenirUtilisateurParId(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (!utilisateur.isPresent()) {
            throw new EntityNotFoundException("Utilisateur non trouvé");
        }
        return utilisateurMapper.toDTO(utilisateur.get());
    }

    @Override
    public void SupprimerUtilisateur(Long id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
        if (!utilisateurOptional.isPresent()) {
            throw new EntityNotFoundException("Utilisateur non trouvé");
        }
        utilisateurRepository.delete(utilisateurOptional.get());
    }



}

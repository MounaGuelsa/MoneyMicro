package org.money.utilisateurmicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.money.utilisateurmicroservice.entities.Utilisateur;
import org.money.utilisateurmicroservice.exceptions.CustomException;
import org.money.utilisateurmicroservice.mappers.UtilisateurMapper;
import org.money.utilisateurmicroservice.repositories.UtilisateurRepository;
import org.money.utilisateurmicroservice.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImp implements UtilisateurService {

    private final UtilisateurMapper utilisateurMapper;
    private final UtilisateurRepository utilisateurRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurServiceImp.class);

    @Override
    public UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto) {
        try {
            Utilisateur utilisateur = utilisateurMapper.toEntity(utilisateurDto);
            utilisateurRepository.save(utilisateur);
            return utilisateurMapper.toDTO(utilisateur);
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de l'ajout de cet utilisateur: {}", e.getMessage());
            throw new CustomException("Erreur lors de l'ajout de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public UtilisateurDto obtenirUtilisateurParId(Long id) {
        try {
            Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
            if (utilisateurOptional.isPresent()) {
                return utilisateurMapper.toDTO(utilisateurOptional.get());
            } else {
                LOGGER.warn(" Utilisateur introuvable avec l'ID: {} ", id);
                throw new CustomException("Utilisateur introuvable avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération de l'utilisateur avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la récupération de l'utilisateur par ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UtilisateurDto modifierUtilisateur(Long id, UtilisateurDto utilisateurDto) {
        try {
            Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
            if (utilisateurOptional.isPresent()) {
                Utilisateur utilisateur = utilisateurOptional.get();
                utilisateur.setNom(utilisateurDto.getNom());
                utilisateur.setPrenom(utilisateurDto.getPrenom());
                utilisateur.setEmail(utilisateurDto.getEmail());
                utilisateur.setPassword(utilisateurDto.getPassword());
                utilisateur.setRole(utilisateurDto.getRole());
                utilisateurRepository.save(utilisateur);
                return utilisateurMapper.toDTO(utilisateur);
            } else {
                LOGGER.warn("Utilisateur introuvable avec cet ID: {}", id);
                throw new CustomException("Utilisateur introuvable avec cet ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la modification de l'utilisateur avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la modification de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        try {
            Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(id);
            if (utilisateurOptional.isPresent()) {
                utilisateurRepository.deleteById(id);
            } else {
                LOGGER.warn("Utilisateur introuvable avec cet ID: {}", id);
                throw new CustomException("Utilisateur introuvable avec cet ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la suppression de l'utilisateur avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la suppression de l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<UtilisateurDto> obtenirUtilisateurs() {
        try {
            List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
            return utilisateurs.stream()
                    .map(utilisateurMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération des utilisateurs: {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des utilisateurs", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

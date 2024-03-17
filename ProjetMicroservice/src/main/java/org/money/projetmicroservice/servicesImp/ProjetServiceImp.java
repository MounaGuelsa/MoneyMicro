package org.money.projetmicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.projetmicroservice.dtos.ProjetDto;
import org.money.projetmicroservice.entities.Projet;
import org.money.projetmicroservice.exceptions.CustomException;
import org.money.projetmicroservice.mappers.ProjetMapper;
import org.money.projetmicroservice.repositories.ProjetRepository;
import org.money.projetmicroservice.services.ProjetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjetServiceImp implements ProjetService {

    private ProjetMapper projetMapper;
    private final ProjetRepository projetRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetServiceImp.class);



    @Override
    public List<ProjetDto> obtenirProjets() {
        try {
            return projetRepository.findAll().stream().map(projetMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération des projets: {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des projets", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProjetDto obtenirProjetParId(Long id) {
        try {
            Optional<Projet> projetOptional = projetRepository.findById(id);
            if (projetOptional.isPresent()) {
                return projetMapper.toDTO(projetOptional.get());
            } else {
                LOGGER.warn("Projet introuvable avec l'ID: {}", id);
                throw new CustomException("Projet introuvable avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération du projet avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la récupération du projet par ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProjetDto ajouterProjet(ProjetDto projetDto) {
        try {
            Projet projet = projetMapper.toEntity(projetDto);
            projetRepository.save(projet);
            return projetMapper.toDTO(projet);
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de l'ajout de ce projet: {}", e.getMessage());
            throw new CustomException("Erreur lors de l'ajout du projet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProjetDto modifierProjet(Long id, ProjetDto projetDto) {
        try {
            Optional<Projet> projetOptional = projetRepository.findById(id);
            if (projetOptional.isPresent()) {
                Projet projet = projetOptional.get();
                // Mettre à jour les attributs du projet
                // Exemple: projet.setNomProjet(projetDto.getNomProjet());
                projetRepository.save(projet);
                return projetMapper.toDTO(projet);
            } else {
                LOGGER.warn("Projet avec cet ID introuvable: {}", id);
                throw new CustomException("Projet avec cet ID introuvable: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la modification du projet avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la modification du projet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimerProjet(Long id) {
        try {
            Optional<Projet> projetOptional = projetRepository.findById(id);
            if (projetOptional.isPresent()) {
                projetRepository.deleteById(id);
            } else {
                LOGGER.warn("Projet avec cet ID introuvable: {}", id);
                throw new CustomException("Projet avec cet ID introuvable: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la suppression du projet avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la suppression du projet", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

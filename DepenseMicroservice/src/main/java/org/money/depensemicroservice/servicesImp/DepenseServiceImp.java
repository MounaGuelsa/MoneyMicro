package org.money.depensemicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.depensemicroservice.entities.Depense;
import org.money.depensemicroservice.dtos.DepenseDto;
import org.money.depensemicroservice.exceptions.CustomException;
import org.money.depensemicroservice.mappers.DepenseMapper;
import org.money.depensemicroservice.repositories.DepenseRepository;
import org.money.depensemicroservice.services.DepenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepenseServiceImp implements DepenseService {

    private final DepenseMapper depenseMapper;
    private final DepenseRepository depenseRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DepenseServiceImp.class);

    @Override
    public List<DepenseDto> obtenirDepenses() {
        try {
            List<Depense> depenses = depenseRepository.findAll();
            return depenses.stream()
                    .map(depenseMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des dépenses : {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des dépenses", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public DepenseDto obtenirDepenseParId(Long id) {
        try {
            Optional<Depense> depenseOptional = depenseRepository.findById(id);
            return depenseOptional.map(depenseMapper::toDTO).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération de la dépense avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la récupération de la dépense par ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public DepenseDto ajouterDepense(DepenseDto depenseDTO) {
        try {
            Depense depense = depenseMapper.toEntity(depenseDTO);
            depense = depenseRepository.save(depense);
            return depenseMapper.toDTO(depense);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de l'ajout de la dépense : {}", e.getMessage());
            throw new CustomException("Erreur lors de l'ajout de la dépense", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public DepenseDto modifierDepense(Long id, DepenseDto depenseDTO) {
        try {
            Optional<Depense> depenseOptional = depenseRepository.findById(id);
            if (depenseOptional.isPresent()) {
                Depense depense = depenseOptional.get();

                depense = depenseRepository.save(depense);
                return depenseMapper.toDTO(depense);
            } else {
                LOGGER.warn("La dépense avec l'ID {} est introuvable.", id);
                throw new CustomException("La dépense avec l'ID " + id + " est introuvable.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la modification de la dépense avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la modification de la dépense", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimerDepense(Long id) {
        try {
            depenseRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la suppression de la dépense avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la suppression de la dépense", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<DepenseDto> obtenirDepensesParUtilisateur(Long utilisateurId) {
        try {
            List<Depense> depenses = depenseRepository.findByUtilisateurId(utilisateurId);
            return depenses.stream()
                    .map(depenseMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des dépenses pour l'utilisateur avec l'ID {} : {}", utilisateurId, e.getMessage());
            throw new CustomException("Erreur lors de la récupération des dépenses pour l'utilisateur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<DepenseDto> obtenirDepensesEntreDates(LocalDate debut, LocalDate fin) {
        try {
            List<Depense> depenses = depenseRepository.findByDateBetween(debut, fin);
            return depenses.stream()
                    .map(depenseMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des dépenses entre les dates {} et {} : {}", debut, fin, e.getMessage());
            throw new CustomException("Erreur lors de la récupération des dépenses entre les dates", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
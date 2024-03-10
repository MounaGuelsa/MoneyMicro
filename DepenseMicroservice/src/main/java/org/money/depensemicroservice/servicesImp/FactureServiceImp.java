package org.money.depensemicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.depensemicroservice.dtos.FactureDto;
import org.money.depensemicroservice.entities.Facture;
import org.money.depensemicroservice.exceptions.CustomException;
import org.money.depensemicroservice.mappers.FactureMapper;
import org.money.depensemicroservice.repositories.FactureRepository;
import org.money.depensemicroservice.services.FactureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FactureServiceImp implements FactureService {

    private final FactureMapper factureMapper;
    private final FactureRepository factureRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(FactureServiceImp.class);

    @Override
    public List<FactureDto> obtenirFactures() {
        try {
            List<Facture> factures = factureRepository.findAll();
            return factures.stream()
                    .map(factureMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des factures : {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des factures", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FactureDto obtenirFactureParId(Long id) {
        try {
            Optional<Facture> factureOptional = factureRepository.findById(id);
            return factureOptional.map(factureMapper::toDTO).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération de la facture avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la récupération de la facture par ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FactureDto ajouterFacture(FactureDto factureDTO) {
        try {
            Facture facture = factureMapper.toEntity(factureDTO);
            facture = factureRepository.save(facture);
            return factureMapper.toDTO(facture);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de l'ajout de la facture : {}", e.getMessage());
            throw new CustomException("Erreur lors de l'ajout de la facture", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FactureDto modifierFacture(Long id, FactureDto factureDTO) {
        try {
            Optional<Facture> factureOptional = factureRepository.findById(id);
            if (factureOptional.isPresent()) {
                Facture facture = factureOptional.get();
                facture = factureRepository.save(facture);
                return factureMapper.toDTO(facture);
            } else {
                LOGGER.warn("La facture avec l'ID {} est introuvable.", id);
                throw new CustomException("La facture avec l'ID " + id + " est introuvable.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la modification de la facture avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la modification de la facture", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimerFacture(Long id) {
        try {
            factureRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la suppression de la facture avec l'ID {} : {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la suppression de la facture", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package org.money.revenuemicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.revenuemicroservice.dtos.RevenueDto;
import org.money.revenuemicroservice.entities.Revenue;
import org.money.revenuemicroservice.exceptions.CustomException;
import org.money.revenuemicroservice.mappers.RevenueMapper;
import org.money.revenuemicroservice.repositories.RevenueRepository;
import org.money.revenuemicroservice.services.RevenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RevenueServiceImp implements RevenueService {

    private final RevenueMapper revenueMapper;
    private final RevenueRepository revenueRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(RevenueServiceImp.class);


    @Override
    public List<RevenueDto> obtenirRevenues() {
        try {
            return revenueRepository.findAll().stream().map(revenueMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération des revenue: {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des revenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public RevenueDto obtenirRevenueParId(Long id) {
        try {
            Optional<Revenue> revenueOptional = revenueRepository.findById(id);
            if (revenueOptional.isPresent()) {
                return revenueMapper.toDTO(revenueOptional.get());
            } else {
                LOGGER.warn("Revenue introuvable avec l'ID: {}", id);
                throw new CustomException("Revenue introuvable avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération du revenue avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la récupération du revenue par ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public RevenueDto ajouterRevenue(RevenueDto revenueDto) {
        try {
            Revenue revenue = revenueMapper.toEntity(revenueDto);
            revenueRepository.save(revenue);
            return revenueMapper.toDTO(revenue);
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de l'ajout de ce revenue: {}", e.getMessage());
            throw new CustomException("Erreur lors de l'ajout du revenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public RevenueDto modifierRevenue(Long id, RevenueDto revenueDto) {
        try {
            Optional<Revenue> revenueOptional = revenueRepository.findById(id);
            if (revenueOptional.isPresent()) {
                Revenue revenue = revenueOptional.get();
                revenue.setNomRevenue(revenueDto.getNomRevenue());
                revenue.setDate(revenueDto.getDate());
                revenue.setMontant(revenueDto.getMontant());
                revenue.setType(revenueDto.getType());
                revenue.setIcone(revenueDto.getIcone());
                revenueRepository.save(revenue);
                return revenueMapper.toDTO(revenue);
            } else {
                LOGGER.warn("Revenue avec cet ID introuvable: {}", id);
                throw new CustomException("Revenue avec cet ID introuvable: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la modification du revenue avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la modification du revenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimerRevenue(Long id) {
        try {
            Optional<Revenue> revenueOptional = revenueRepository.findById(id);
            if (revenueOptional.isPresent()) {
                revenueRepository.deleteById(id);
            } else {
                LOGGER.warn("Revenue avec cet ID introuvable: {}", id);
                throw new CustomException("Revenue avec cet ID introuvable: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la suppression du revenue avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la suppression du revenue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<RevenueDto> rechercherRevenuesParNom(String nom) {
        try {
            if (StringUtils.hasText(nom)) {
                List<Revenue> revenus = revenueRepository.findByNomRevenueContainingIgnoreCase(nom);
                return revenus.stream()
                        .map(revenueMapper::toDTO)
                        .collect(Collectors.toList());
            } else {
                return obtenirRevenues(); // Retourner tous les revenus si aucun nom de recherche n'est spécifié
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la recherche des revenus par nom: {}", e.getMessage());
            throw new CustomException("Erreur lors de la recherche des revenus par nom", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Double totalRevenuesParMois() {
        try {

            List<Revenue> revenues = revenueRepository.findDepensesByCurrentMonth();
            double total = 0.0;


            for (Revenue revenue : revenues) {
                total += revenue.getMontant();
            }

            return total;
        } catch (Exception e) {
            LOGGER.error("Erreur lors du calcul du total des revenues : {}", e.getMessage());
            throw new CustomException("Erreur lors du calcul du total des revenues", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
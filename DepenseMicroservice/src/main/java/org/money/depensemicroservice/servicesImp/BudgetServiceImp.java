package org.money.depensemicroservice.servicesImp;

import lombok.RequiredArgsConstructor;
import org.money.depensemicroservice.dtos.BudgetDto;
import org.money.depensemicroservice.entities.Budget;
import org.money.depensemicroservice.exceptions.CustomException;
import org.money.depensemicroservice.mappers.BudgetMapper;
import org.money.depensemicroservice.repositories.BudgetRepository;
import org.money.depensemicroservice.services.BudgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetServiceImp implements BudgetService {

    private final BudgetMapper budgetMapper;
    private final BudgetRepository budgetRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetServiceImp.class);

    @Override
    public List<BudgetDto> obtenirBudgets() {
        try {
            return budgetRepository.findAll().stream().map(budgetMapper::toDTO).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération des budgets: {}", e.getMessage());
            throw new CustomException("Erreur lors de la récupération des budgets", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BudgetDto obtenirBudgetParId(Long id) {
        try {
            Optional<Budget> budgetOptional = budgetRepository.findById(id);
            if (budgetOptional.isPresent()) {
                return budgetMapper.toDTO(budgetOptional.get());
            } else {
                LOGGER.warn("Budget introuvable avec l'ID: {}", id);
                throw new CustomException("Budget introuvable avec l'ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la récupération du budget avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la récupération du budget par ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BudgetDto ajouterBudget(BudgetDto budgetDTO) {
        try {
            Budget budget = budgetMapper.toEntity(budgetDTO);
            budgetRepository.save(budget);
            return budgetMapper.toDTO(budget);
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de l'ajout de ce budget: {}", e.getMessage());
            throw new CustomException("Erreur lors de l'ajout du budget", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BudgetDto modifierBudget(Long id, BudgetDto budgetDTO) {
        try {
            Optional<Budget> budgetOptional = budgetRepository.findById(id);
            if (budgetOptional.isPresent()) {
                Budget budget = budgetOptional.get();
                budget.setNomBudget(budgetDTO.getNomBudget());
                budget.setMois(budgetDTO.getMois());
                budget.setMontant(budgetDTO.getMontant());
                //budget.setCategorie(budgetDTO.getCategorie()); // Assurez-vous d'ajouter cette fonctionnalité si nécessaire
                budgetRepository.save(budget);
                return budgetMapper.toDTO(budget);
            } else {
                LOGGER.warn("Budget avec cet ID introuvable: {}", id);
                throw new CustomException("Budget avec cet ID introuvable: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la modification du budget avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la modification du budget", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void supprimmerBudget(Long id) {
        try {
            Optional<Budget> budgetOptional = budgetRepository.findById(id);
            if (budgetOptional.isPresent()) {
                budgetRepository.deleteById(id);
            } else {
                LOGGER.warn("Budget avec cet ID introuvable: {}", id);
                throw new CustomException("Budget avec cet ID introuvable: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Une erreur s'est produite lors de la suppression du budget avec ID {}: {}", id, e.getMessage());
            throw new CustomException("Erreur lors de la suppression du budget", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

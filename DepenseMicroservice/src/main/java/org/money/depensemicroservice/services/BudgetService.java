package org.money.depensemicroservice.services;

import org.money.depensemicroservice.dtos.BudgetDto;

import java.util.List;

public interface BudgetService {
    List<BudgetDto> obtenirBudgets();
    BudgetDto obtenirBudgetParId(Long id);
    BudgetDto ajouterBudget(BudgetDto budgetDTO);
    BudgetDto modifierBudget(Long id,BudgetDto budgetDTO);
    void supprimmerBudget(Long id);
}

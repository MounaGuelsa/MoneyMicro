package org.money.depensemicroservice.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;
import org.money.depensemicroservice.entities.Budget;

import java.io.Serializable;
import java.time.YearMonth;

/**
 * DTO for {@link Budget}
 */
@Value
public class BudgetDto implements Serializable {
    @NotNull
    Long idBudget;
    @NotBlank
    String nomBudget;
    @NotNull
    YearMonth mois;
    @Positive
    double montant;
    @NotNull
    CategorieDto categorie;
}
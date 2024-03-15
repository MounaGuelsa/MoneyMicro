package org.money.depensemicroservice.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;
import org.money.depensemicroservice.entities.Budget;
import org.money.depensemicroservice.entities.Categorie;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Date;

/**
 * DTO for {@link Budget}
 */
@Value
@Builder
public class BudgetDto implements Serializable {
    @NotNull
    Long idBudget;
    @NotNull
    Date mois;
    @Positive
    double montant;
    @NotNull
    @JsonIgnoreProperties(value = {"budget"})
    CategorieDto categorie;
}
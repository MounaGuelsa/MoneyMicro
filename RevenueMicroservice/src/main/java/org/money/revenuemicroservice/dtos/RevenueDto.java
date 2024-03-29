package org.money.revenuemicroservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;
import org.money.revenuemicroservice.entities.Type;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.money.revenuemicroservice.entities.Revenue}
 */
@Value
public class RevenueDto implements Serializable {
    @NotNull
    Long idRevenue;
    @NotBlank
    String nomRevenue;
    @Positive
    double montant;
    @NotNull
    LocalDate date;
    @NotNull
    Long utilisateurId;
    @NotNull
    Type type;
    String icone;
}
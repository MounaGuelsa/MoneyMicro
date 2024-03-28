package org.money.rapportmicroservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.money.rapportmicroservice.entities.Rapport}
 */
@Value
public class RapportDto implements Serializable {
    @NotNull
    Long idRapport;
    @NotBlank
    String moisAnnee;
}
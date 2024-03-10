package org.money.depensemicroservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.money.depensemicroservice.entities.Depense;

import java.io.Serializable;

/**
 * DTO for {@link org.money.depensemicroservice.entities.Facture}
 */
@Value
public class FactureDto implements Serializable {
    @NotBlank
    Long numeroFacture;
    @NotBlank
    String fournisseur;
    @NotNull
    Depense depense;
}
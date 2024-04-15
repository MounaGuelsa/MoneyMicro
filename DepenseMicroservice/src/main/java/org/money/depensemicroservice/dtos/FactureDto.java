package org.money.depensemicroservice.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.money.depensemicroservice.entities.Facture}
 */
@Data

@Builder
public class FactureDto implements Serializable {
    @NotBlank
    Long numeroFacture;
    @NotBlank
    String nomFacture;
    //@NotNull
    DepenseDto depense;
    String url;


}
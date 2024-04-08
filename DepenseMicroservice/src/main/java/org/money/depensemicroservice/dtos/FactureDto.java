package org.money.depensemicroservice.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.money.depensemicroservice.entities.Facture}
 */
@Value
@Builder

public class FactureDto implements Serializable {
    @NotBlank
    Long numeroFacture;
    @NotBlank
    String nomFacture;
    //@NotNull
    DepenseDto depense;
    String url;

    public static FactureDto createFactureDto(Long numeroFacture, String nomFacture, String url) {
        return FactureDto.builder()
                .numeroFacture(numeroFacture)
                .nomFacture(nomFacture)
                .url(url)
                .build();
    }
}
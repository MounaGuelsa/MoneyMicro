package org.money.projetmicroservice.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import org.money.depensemicroservice.dtos.DepenseDto;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link org.money.projetmicroservice.entities.Projet}
 */
@Value
@Builder
public class ProjetDto implements Serializable {
    @NotNull
    Long idProjet;
    @NotNull
    String nomProjet;
}
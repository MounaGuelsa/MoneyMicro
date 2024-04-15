package org.money.rapportmicroservice.Depense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.money.depensemicroservice.entities.Categorie}
 */
@Value
public class CategorieDto implements Serializable {
    @NotNull
    Long idCategorie;
    @NotBlank
    String nom;
//    @NotNull
//    @JsonIgnoreProperties(value = {"categorie"})
//    Budget budget;
}
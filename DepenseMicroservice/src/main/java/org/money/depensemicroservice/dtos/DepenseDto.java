package org.money.depensemicroservice.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Value;
import org.money.depensemicroservice.entities.Categorie;
import org.money.depensemicroservice.entities.Depense;
import org.money.depensemicroservice.entities.MoyennePaiement;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Depense}
 */
@Value
public class DepenseDto implements Serializable {
    @NotNull
    Long idDepense;
    @PositiveOrZero
    double montant;
    @FutureOrPresent
    LocalDate date;
    @NotNull
    CategorieDto categorie;
    FactureDto facture;
    String notes;
    String description;

    @NotNull
    MoyennePaiement moyennePaiement;
    @NotNull
    Long utilisateurId;
}
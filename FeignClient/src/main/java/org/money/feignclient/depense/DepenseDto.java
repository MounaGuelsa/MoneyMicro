package org.money.feignclient.depense;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Value;


import java.io.Serializable;
import java.time.LocalDate;


@Value
public class DepenseDto implements Serializable {
    @NotNull
    Long idDepense;
    @PositiveOrZero
    double montant;
    @FutureOrPresent
    LocalDate date;
    @NotNull
    Long categorie;
    Long facture;
    String notes;
    String description;
    @NotNull
    MoyennePaiement moyennePaiement;
    @NotNull
    Long utilisateurId;
}
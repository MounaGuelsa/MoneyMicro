package org.money.projetmicroservice.dtos.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.money.depensemicroservice.dtos.CategorieDto;
import org.money.depensemicroservice.dtos.FactureDto;
import org.money.depensemicroservice.entities.MoyennePaiement;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjetDepenseRequestDto {

    @NotBlank
    private String nomProjet;
    private List<ProjetDepenseRequestDto.DepenseDto> depenses;

    @Data
    @NoArgsConstructor
    public static class DepenseDto {
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
}

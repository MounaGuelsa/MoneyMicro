package org.money.rapportmicroservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.money.rapportmicroservice.entities.Rapport;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Rapport}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RapportDto implements Serializable {
    Long idRapport;
    LocalDate moisAnnee;
    Double depenses;
    Double revenus;
    Double balance;



}
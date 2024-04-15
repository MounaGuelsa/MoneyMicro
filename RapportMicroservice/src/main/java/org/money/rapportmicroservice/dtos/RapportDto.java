package org.money.rapportmicroservice.dtos;


import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.money.rapportmicroservice.entities.Rapport;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Rapport}
 */
@Data
@Builder
public class RapportDto implements Serializable {
    Long idRapport;
    LocalDate moisAnnee;
    Double depenses;
    Double revenus;
    Double balance;
    public RapportDto() {

    }


}
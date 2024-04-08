package org.money.rapportmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatistiqueDto {
    private Double depenses;
    private Double revenus;
    private Double balance;
}

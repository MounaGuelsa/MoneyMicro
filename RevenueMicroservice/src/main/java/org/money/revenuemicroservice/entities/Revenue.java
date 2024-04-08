package org.money.revenuemicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRevenue;
    private String nomRevenue;
    private double montant;
    private LocalDate date;
    private Long utilisateurId;
    private Type type;
    private String icone;
}




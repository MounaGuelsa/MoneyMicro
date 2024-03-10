package org.money.depensemicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBudget;
    private String nomBudget;
    private YearMonth mois;
    private double montant;
    @OneToOne
    private Categorie categorie;
}

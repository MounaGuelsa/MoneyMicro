package org.money.depensemicroservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long numeroFacture;

    private String fournisseur;

    @OneToOne
    @JoinColumn(name = "idDepense")
    private Depense depense;
}
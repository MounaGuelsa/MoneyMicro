package org.money.depensemicroservice.entities;


import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Builder
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroFacture;
    private String nomFacture;
    private String url;
    @OneToOne
    @JoinColumn(name = "idDepense")
    private Depense depense;
}
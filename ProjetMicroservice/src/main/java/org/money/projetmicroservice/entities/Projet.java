package org.money.projetmicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.money.depensemicroservice.entities.Depense;


import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjet;

    private String nomProjet;
    @ManyToMany
    @JoinTable(
            name = "projet_depense",
            joinColumns = @JoinColumn(name = "idProjet"),
            inverseJoinColumns = @JoinColumn(name = "idDepense")
    )

    private Set<Depense> listeDepenses = new HashSet<>();



}
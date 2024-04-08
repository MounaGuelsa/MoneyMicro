package org.money.projetmicroservice.entities;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjet;
    @Column(nullable = false)
    private String nomProjet;
    @Column(nullable = false)
    private Double total;
    @ElementCollection
    private Set<Long> deponses;
}

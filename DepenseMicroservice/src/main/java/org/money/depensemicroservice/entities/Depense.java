package org.money.depensemicroservice.entities;





import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepense;
    private double montant;
    private LocalDate date;
    private String description;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "idCategorie")
    private Categorie categorie;
    @OneToOne(mappedBy = "depense")
    private Facture facture;
    private MoyennePaiement moyennePaiement;
    private Long utilisateurId;

}

//package org.money.depensemicroservice.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.mapstruct.Mapping;
//import org.money.depensemicroservice.dtos.BudgetDto;
//
//import java.util.Date;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Budget {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idBudget;
//
//    private Date mois;
//    private double montant;
////    @JsonIgnore
////    @OneToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
////    private Categorie categorie;
//
//}

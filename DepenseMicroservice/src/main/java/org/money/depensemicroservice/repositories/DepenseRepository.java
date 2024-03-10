package org.money.depensemicroservice.repositories;

import org.money.depensemicroservice.entities.Depense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DepenseRepository extends JpaRepository<Depense, Long> {
    List<Depense> findByDateBetween(LocalDate debut, LocalDate fin);

    List<Depense> findByUtilisateurId(Long utilisateurId);
}
package org.money.depensemicroservice.repositories;

import org.money.depensemicroservice.entities.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository

public interface DepenseRepository extends JpaRepository<Depense, Long> {
    List<Depense> findByDateBetween(LocalDate debut, LocalDate fin);

    List<Depense> findByUtilisateurId(Long utilisateurId);
}
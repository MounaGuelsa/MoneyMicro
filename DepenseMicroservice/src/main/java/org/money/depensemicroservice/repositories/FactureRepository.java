package org.money.depensemicroservice.repositories;

import org.money.depensemicroservice.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {
}
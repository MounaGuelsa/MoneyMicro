package org.money.rapportmicroservice.repositories;

import org.money.rapportmicroservice.entities.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
}
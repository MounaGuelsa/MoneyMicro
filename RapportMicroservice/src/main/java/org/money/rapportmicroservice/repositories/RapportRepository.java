package org.money.rapportmicroservice.repositories;

import org.money.rapportmicroservice.entities.Rapport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RapportRepository extends JpaRepository<Rapport, Long> {

    List<Rapport> findByMoisAnneeBetween(LocalDate dateDebut, LocalDate dateFin);
}
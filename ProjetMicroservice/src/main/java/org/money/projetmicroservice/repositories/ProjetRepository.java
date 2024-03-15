package org.money.projetmicroservice.repositories;

import org.money.projetmicroservice.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
}
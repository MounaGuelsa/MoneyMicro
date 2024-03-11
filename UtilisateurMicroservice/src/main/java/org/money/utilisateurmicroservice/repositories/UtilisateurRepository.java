package org.money.utilisateurmicroservice.repositories;

import org.money.utilisateurmicroservice.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
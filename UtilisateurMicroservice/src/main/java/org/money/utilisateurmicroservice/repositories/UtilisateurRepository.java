package org.money.utilisateurmicroservice.repositories;

import org.money.utilisateurmicroservice.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
//    Optional<Utilisateur> findByEmailAndNom_utilisateur(
//            String email,
//            String nomUtilisateur
//    );
}
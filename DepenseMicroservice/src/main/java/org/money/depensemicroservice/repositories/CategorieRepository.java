package org.money.depensemicroservice.repositories;

import org.money.depensemicroservice.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
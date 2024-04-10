package org.money.depensemicroservice.repositories;

import org.money.depensemicroservice.entities.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository

public interface DepenseRepository extends JpaRepository<Depense, Long> {
    @Query("SELECT d FROM Depense d WHERE MONTH(d.date) = MONTH(CURRENT_DATE)")
    List<Depense> findDepensesByCurrentMonth();
}
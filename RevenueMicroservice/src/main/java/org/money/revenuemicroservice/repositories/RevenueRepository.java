package org.money.revenuemicroservice.repositories;

import org.money.revenuemicroservice.dtos.RevenueDto;
import org.money.revenuemicroservice.entities.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    List<Revenue> findByNomRevenueContainingIgnoreCase(String nom);
    @Query("SELECT d FROM Revenue d WHERE MONTH(d.date) = MONTH(CURRENT_DATE)")
    List<Revenue> findDepensesByCurrentMonth();
}
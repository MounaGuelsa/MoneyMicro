package org.money.depensemicroservice.repositories;

import org.money.depensemicroservice.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
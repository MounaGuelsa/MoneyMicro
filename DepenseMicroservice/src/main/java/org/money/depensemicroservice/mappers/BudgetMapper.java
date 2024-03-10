package org.money.depensemicroservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.money.depensemicroservice.entities.Budget;
import org.money.depensemicroservice.dtos.BudgetDto;
import org.springframework.stereotype.Component;

@Component

@Mapper(componentModel="spring")
public interface BudgetMapper extends GlobalMapper<BudgetDto, Budget> {
    BudgetMapper INSTANCE = Mappers.getMapper(BudgetMapper.class);

    @Mapping(target = "categorie",source ="categorie")
    BudgetDto toDTO(Budget budget);
    @Mapping(target = "categorie",source ="categorie")
    Budget toEntity(BudgetDto budgetDTO);
}
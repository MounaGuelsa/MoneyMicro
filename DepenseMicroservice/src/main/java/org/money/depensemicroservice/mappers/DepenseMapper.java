package org.money.depensemicroservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.money.depensemicroservice.entities.Depense;
import org.money.depensemicroservice.dtos.DepenseDto;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
@Component

public interface DepenseMapper extends GlobalMapper<DepenseDto, Depense> {
    DepenseMapper INSTANCE = Mappers.getMapper(DepenseMapper.class);

    @Mapping(target = "categorie",source ="categorie")
    @Mapping(target = "facture",source ="facture")
    @Mapping(target = "utilisateurId",source ="utilisateurId")

    DepenseDto toDTO (Depense depense);
    @Mapping(target = "categorie",source ="categorie")
    @Mapping(target = "facture",source ="facture")
    @Mapping(target = "utilisateurId",source ="utilisateurId")
    Depense toEntity(DepenseDto depenseDTO);
}

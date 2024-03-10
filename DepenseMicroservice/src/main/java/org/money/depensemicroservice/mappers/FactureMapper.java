package org.money.depensemicroservice.mappers;

import org.mapstruct.factory.Mappers;
import org.money.depensemicroservice.dtos.FactureDto;
import org.money.depensemicroservice.entities.Facture;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
@Component

public interface FactureMapper extends GlobalMapper<FactureDto, Facture> {
    FactureMapper INSTANCE = Mappers.getMapper(FactureMapper.class);

    @Mapping(target = "depense",source ="depense")
    FactureDto toDTO(Facture facture);
    @Mapping(target = "depense",source ="depense")
    Facture toEntity(FactureDto factureDTO);
}

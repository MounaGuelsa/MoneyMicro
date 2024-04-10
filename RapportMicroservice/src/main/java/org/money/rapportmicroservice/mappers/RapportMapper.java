package org.money.rapportmicroservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.money.rapportmicroservice.dtos.RapportDto;
import org.money.rapportmicroservice.entities.Rapport;
import org.springframework.stereotype.Component;
@Mapper(componentModel="spring")
@Component
public interface RapportMapper {

    RapportMapper INSTANCE = Mappers.getMapper(RapportMapper.class);
    RapportDto toDTO (Rapport rapport);
    Rapport toEntity (RapportDto rapportDto);
}

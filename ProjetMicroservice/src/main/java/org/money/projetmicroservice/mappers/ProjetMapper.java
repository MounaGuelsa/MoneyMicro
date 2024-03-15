package org.money.projetmicroservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.money.projetmicroservice.dtos.ProjetDto;
import org.money.projetmicroservice.entities.Projet;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
@Component
public interface ProjetMapper {
    ProjetMapper INSTANCE = Mappers.getMapper(ProjetMapper.class);
    ProjetDto toDTO(Projet p);
    Projet toEntity(ProjetDto p);
}

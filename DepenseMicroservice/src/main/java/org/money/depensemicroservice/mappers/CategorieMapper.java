package org.money.depensemicroservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.money.depensemicroservice.dtos.CategorieDto;
import org.money.depensemicroservice.entities.Categorie;
import org.springframework.stereotype.Component;

@Component

@Mapper(componentModel="spring")
public interface CategorieMapper extends  GlobalMapper<CategorieDto, Categorie> {
    CategorieMapper INSTANCE = Mappers.getMapper(CategorieMapper.class);


    CategorieDto toDTO(Categorie categorie);

    Categorie toEntity(Categorie categorieDto);
}

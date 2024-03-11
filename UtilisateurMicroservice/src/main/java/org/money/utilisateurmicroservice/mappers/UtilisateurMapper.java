package org.money.utilisateurmicroservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.money.utilisateurmicroservice.entities.Utilisateur;

@Mapper(componentModel="spring")
public interface UtilisateurMapper {
    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);
    UtilisateurDto toDTO(Utilisateur e);
    Utilisateur toEntity(UtilisateurDto o);
}
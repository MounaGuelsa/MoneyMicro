package org.money.utilisateurmicroservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.money.utilisateurmicroservice.dtos.UtilisateurDto;
import org.money.utilisateurmicroservice.entities.Utilisateur;

@Mapper(componentModel="spring")
public interface UtilisateurMapper {
    UtilisateurDto INSTANCE = Mappers.getMapper(UtilisateurDto.class);
    UtilisateurDto toDTO(Utilisateur utilisateur);
    Utilisateur toEntity(UtilisateurDto utilisateurDto);
}

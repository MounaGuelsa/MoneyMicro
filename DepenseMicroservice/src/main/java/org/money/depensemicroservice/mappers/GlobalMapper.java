package org.money.depensemicroservice.mappers;

public interface GlobalMapper<DTO,ENTITY>{
    DTO toDTO(ENTITY e);
    ENTITY toEntity(DTO o);
}
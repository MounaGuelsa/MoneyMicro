package org.money.revenuemicroservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.money.revenuemicroservice.dtos.RevenueDto;
import org.money.revenuemicroservice.entities.Revenue;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
@Component
public interface RevenueMapper {
    RevenueMapper INSTANCE = Mappers.getMapper(RevenueMapper.class);
    RevenueDto toDTO(Revenue r);
    Revenue toEntity(RevenueDto r);
}

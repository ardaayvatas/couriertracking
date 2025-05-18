package com.ardaayvatas.couriertracking.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CoreMapper {
    CoreMapper INSTANCE = Mappers.getMapper(CoreMapper.class);
}

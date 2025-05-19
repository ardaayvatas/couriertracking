package com.ardaayvatas.couriertracking.data.mapper;

import com.ardaayvatas.couriertracking.data.dao.model.Store;
import com.ardaayvatas.couriertracking.data.dto.StoreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    List<StoreDTO> toStoreDTOList(List<Store> store);
}

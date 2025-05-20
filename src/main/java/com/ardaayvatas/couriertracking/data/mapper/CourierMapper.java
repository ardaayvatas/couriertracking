package com.ardaayvatas.couriertracking.data.mapper;

import com.ardaayvatas.couriertracking.data.dao.model.Courier;
import com.ardaayvatas.couriertracking.data.dao.model.CourierDistance;
import com.ardaayvatas.couriertracking.data.dao.model.CourierLocation;
import com.ardaayvatas.couriertracking.data.dao.model.CourierStoreEntrance;
import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierDistanceDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierStoreEntranceDTO;
import com.ardaayvatas.couriertracking.data.request.RequestCreateCourier;
import com.ardaayvatas.couriertracking.data.request.RequestCreateCourierLocation;
import com.ardaayvatas.couriertracking.data.response.ResponseCreateCourier;
import com.ardaayvatas.couriertracking.data.response.ResponseCreateCourierLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourierMapper {
    CourierMapper INSTANCE = Mappers.getMapper(CourierMapper.class);

    @Mapping(source = "courierDTO.id", target = "courier.id")
    CourierLocation toCourierLocation(CourierLocationDTO dto);

    @Mapping(source = "courier.id", target = "courierDTO.id")
    List<CourierLocationDTO> toCourierLocationDTO(List<CourierLocation> entity);

    @Mapping(source = "courier.id", target = "courierDTO.id")
    @Mapping(source = "courier.name", target = "courierDTO.name")
    @Mapping(source = "courier.surname", target = "courierDTO.surname")
    @Mapping(source = "store.id", target = "storeDTO.id")
    @Mapping(source = "store.name", target = "storeDTO.name")
    @Mapping(source = "store.lat", target = "storeDTO.lat")
    @Mapping(source = "store.lng", target = "storeDTO.lng")
    List<CourierStoreEntranceDTO> toCourierStoreEntranceDTO(List<CourierStoreEntrance> entity);

    @Mapping(source = "courierDTO.id", target = "courier.id")
    @Mapping(source = "storeDTO.id", target = "store.id")
    @Mapping(source = "courierDTO.name", target = "courier.name")
    @Mapping(source = "courierDTO.surname", target = "courier.surname")
    @Mapping(source = "storeDTO.name", target = "store.name")
    @Mapping(source = "storeDTO.lat", target = "store.lat")
    @Mapping(source = "storeDTO.lng", target = "store.lng")
    CourierStoreEntrance toCourierStoreEntrance(CourierStoreEntranceDTO dto);

    CourierDTO toCourierDTO(Courier courier);

    CourierDTO requestCreateCourierToCourier(RequestCreateCourier requestCreateCourier);

    Courier toCourier(CourierDTO courierDTO);

    ResponseCreateCourier toResponseCreateCourier(Courier courier);

    @Mapping(source = "courierId", target = "courierDTO.id")
    CourierLocationDTO requestCreateCourierLocationToCourierLocationDTO(RequestCreateCourierLocation requestCreateCourierLocation);

    @Mapping(source = "courierDTO.id", target = "courierId")
    ResponseCreateCourierLocation courierLocationDTOToResponseCreateCourierLocation(CourierLocationDTO courierLocation);

    List<CourierDistance> toCourierDistance(List<CourierDistanceDTO> courierDistanceDTO);

    @Mapping(source = "courierDTO", target = "courier")
    CourierDistance toCourierDistance(CourierDistanceDTO courierDistanceDTO);
}

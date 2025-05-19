package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.common.exception.BusinessException;
import com.ardaayvatas.couriertracking.data.dao.repository.CourierLocationRepository;
import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.enumeration.ExceptionType;
import com.ardaayvatas.couriertracking.event.publisher.CourierLocationPublisher;
import com.ardaayvatas.couriertracking.service.intf.CourierLocationService;
import com.ardaayvatas.couriertracking.service.intf.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierLocationServiceImpl implements CourierLocationService {
    private final CourierLocationRepository courierLocationRepository;
    private final CourierMapper courierMapper;
    private final CourierLocationPublisher courierLocationPublisher;
    private final CourierService courierService;

    @Override
    public CourierLocationDTO createCourierLocation(CourierLocationDTO courierLocationDTO) {
        if (isExistCourier(courierLocationDTO.getCourierDTO().getId())){
            courierLocationRepository.save(courierMapper.toCourierLocation(courierLocationDTO));
            courierLocationPublisher.publishLocationEvent(courierLocationDTO);
            return courierLocationDTO;
        } else {
            throw new BusinessException(ExceptionType.COURIER_NOT_FOUND);
        }
    }

    private Boolean isExistCourier(Long id) {
        return courierService.existById(id);
    }
}

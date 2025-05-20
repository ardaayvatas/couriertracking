package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.common.exception.BusinessException;
import com.ardaayvatas.couriertracking.data.dao.model.CourierDistance;
import com.ardaayvatas.couriertracking.data.dao.repository.CourierDistanceRepository;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.data.response.ResponseCourierDistance;
import com.ardaayvatas.couriertracking.enumeration.ExceptionType;
import com.ardaayvatas.couriertracking.service.intf.CourierDistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierDistanceServiceImpl implements CourierDistanceService {
    private final CourierDistanceRepository courierDistanceRepository;
    private final CourierMapper courierMapper;
    @Override
    public ResponseCourierDistance findByCourierId(Long id) {
        Optional<CourierDistance> courierDistanceOptional = courierDistanceRepository.findByCourierId(id);
        if (courierDistanceOptional.isPresent()) {
            return courierMapper.toResponseCourierDistance(courierDistanceOptional.get());
        } else {
            throw new BusinessException(ExceptionType.COURIER_DISTANCE_ERROR);
        }
    }
}

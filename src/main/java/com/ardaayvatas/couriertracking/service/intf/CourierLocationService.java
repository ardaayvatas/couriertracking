package com.ardaayvatas.couriertracking.service.intf;

import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CourierLocationService {
    CourierLocationDTO createCourierLocation(CourierLocationDTO courierLocationDTO);

    List<CourierLocationDTO> findByCourierIdAndCreatedDateBetween(Long courierId, LocalDateTime startDate, LocalDateTime endDate);
}

package com.ardaayvatas.couriertracking.service.intf;

import com.ardaayvatas.couriertracking.data.dao.model.CourierStoreEntrance;
import com.ardaayvatas.couriertracking.data.dto.CourierStoreEntranceDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CourierStoreEntranceService {
    Boolean existsByCourierIdAndStoreIdAndCreatedDateAfter(Long courierId, Long storeId, LocalDateTime createdDate);
    void save(CourierStoreEntrance courierStoreEntrance);
    List<CourierStoreEntranceDTO> findByCourierIdAndCreatedDateBetween(Long courierId, LocalDateTime startDate, LocalDateTime endDate);
}

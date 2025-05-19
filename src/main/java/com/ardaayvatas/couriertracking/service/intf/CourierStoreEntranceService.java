package com.ardaayvatas.couriertracking.service.intf;

import com.ardaayvatas.couriertracking.data.dao.model.CourierStoreEntrance;

import java.time.LocalDateTime;

public interface CourierStoreEntranceService {
    Boolean existsByCourierIdAndStoreIdAndCreatedDateAfter(Long courierId, Long storeId, LocalDateTime createdDate);
    void save(CourierStoreEntrance courierStoreEntrance);
}

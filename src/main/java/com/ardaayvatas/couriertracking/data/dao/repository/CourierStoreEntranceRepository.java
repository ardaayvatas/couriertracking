package com.ardaayvatas.couriertracking.data.dao.repository;

import com.ardaayvatas.couriertracking.data.dao.model.CourierStoreEntrance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CourierStoreEntranceRepository extends JpaRepository<CourierStoreEntrance, Long> {
    Boolean existsByCourierIdAndStoreIdAndCreatedDateAfter(Long courierId, Long storeId, LocalDateTime createdDate);
}
package com.ardaayvatas.couriertracking.data.dao.repository;

import com.ardaayvatas.couriertracking.data.dao.model.CourierDistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierDistanceRepository extends JpaRepository<CourierDistance, Long> {
    Optional<CourierDistance> findByCourierId(Long courierId);
}


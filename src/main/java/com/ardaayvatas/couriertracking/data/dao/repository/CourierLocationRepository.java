package com.ardaayvatas.couriertracking.data.dao.repository;

import com.ardaayvatas.couriertracking.data.dao.model.CourierLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierLocationRepository extends JpaRepository<CourierLocation, Long> {}



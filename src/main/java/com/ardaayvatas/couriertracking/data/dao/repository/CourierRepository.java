package com.ardaayvatas.couriertracking.data.dao.repository;

import com.ardaayvatas.couriertracking.data.dao.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {}


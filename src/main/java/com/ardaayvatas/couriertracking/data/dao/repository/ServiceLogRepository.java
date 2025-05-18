package com.ardaayvatas.couriertracking.data.dao.repository;

import com.ardaayvatas.couriertracking.data.dao.model.ServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceLogRepository extends JpaRepository<ServiceLog, Long> {}


package com.ardaayvatas.couriertracking.data.dao.repository;

import com.ardaayvatas.couriertracking.data.dao.model.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {}

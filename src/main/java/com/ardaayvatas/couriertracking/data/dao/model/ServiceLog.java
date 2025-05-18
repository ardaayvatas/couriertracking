package com.ardaayvatas.couriertracking.data.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_log")
@Getter
@Setter
public class ServiceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SERVICE_NAME", length = 200, nullable = false)
    private String serviceName;

    @Column(name = "METHOD_NAME", length = 200, nullable = false)
    private String methodName;

    @Column(name = "REQUEST", length = 4000)
    private String request;

    @Column(name = "RESPONSE", length = 4000)
    private String response;

    @Column(name = "LOG_DATE", nullable = false)
    private LocalDateTime logDate;
}

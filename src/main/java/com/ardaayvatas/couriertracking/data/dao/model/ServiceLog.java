package com.ardaayvatas.couriertracking.data.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "SERVICE_LOG")
@Getter
@Setter
public class ServiceLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1758589907129431L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 16)
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

package com.ardaayvatas.couriertracking.data.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ERROR_LOG")
@Getter
@Setter
public class ErrorLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1204199907129999L;

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

    @Column(name = "ERROR_DETAIL", length = 4000)
    private String errorDetail;

    @Column(name = "ERROR_DATE", nullable = false)
    private LocalDateTime errorDate;
}


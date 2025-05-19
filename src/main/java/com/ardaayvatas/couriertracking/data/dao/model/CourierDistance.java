package com.ardaayvatas.couriertracking.data.dao.model;

import com.ardaayvatas.couriertracking.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "COURIER_DISTANCES")
public class CourierDistance extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5236791907104311L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 16)
    private Long id;

    @OneToOne
    @JoinColumn(name = "COURIER_ID")
    private Courier courier;

    @Column(name = "TOTAL_DISTANCE", precision = 25, scale = 2)
    private BigDecimal totalDistance;
}
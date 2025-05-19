package com.ardaayvatas.couriertracking.data.dao.model;

import com.ardaayvatas.couriertracking.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "COURIER_LOCATIONS")
public class CourierLocation extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7311771043191901L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 16)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COURIER_ID")
    private Courier courier;

    @Column(name = "LAT", precision = 10, scale = 8)
    private Double lat;

    @Column(name = "LNG", precision = 10, scale = 8)
    private Double lng;
}

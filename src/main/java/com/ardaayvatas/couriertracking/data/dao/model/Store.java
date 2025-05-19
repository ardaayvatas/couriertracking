package com.ardaayvatas.couriertracking.data.dao.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "STORES")
public class Store implements Serializable {

    @Serial
    private static final long serialVersionUID = 4780194334044811L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 16)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LNG")
    private Double lng;
}

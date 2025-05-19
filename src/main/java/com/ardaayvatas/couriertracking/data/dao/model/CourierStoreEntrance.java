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
@Table(name = "COURIER_STORE_ENTRANCE")
public class CourierStoreEntrance extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4334784044801933L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 16)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COURIER_ID")
    private Courier courier;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;
}

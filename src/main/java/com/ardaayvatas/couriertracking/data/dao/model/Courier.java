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
@Table(name = "COURIERS")
public class Courier extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1202199907042001L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 16)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;
    
    @Column(name = "SURNAME", length = 100)
    private String surname;
}

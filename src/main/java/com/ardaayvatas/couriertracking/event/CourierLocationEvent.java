package com.ardaayvatas.couriertracking.event;

import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import org.springframework.context.ApplicationEvent;

public class CourierLocationEvent extends ApplicationEvent {

    private final CourierLocationDTO courierLocation;

    public CourierLocationEvent(Object source, CourierLocationDTO courierLocation) {
        super(source);
        this.courierLocation = courierLocation;
    }

    public CourierLocationDTO getLocation() {
        return courierLocation;
    }
}

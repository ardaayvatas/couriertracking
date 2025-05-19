package com.ardaayvatas.couriertracking.event.publisher;

import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.event.CourierLocationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CourierLocationPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public CourierLocationPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishLocationEvent(CourierLocationDTO location) {
        CourierLocationEvent event = new CourierLocationEvent(this, location);
        eventPublisher.publishEvent(event);
    }
}

package com.ardaayvatas.couriertracking.event.publisher;

import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.event.CourierLocationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourierLocationPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public void publishLocationEvent(CourierLocationDTO location) {
        CourierLocationEvent event = new CourierLocationEvent(this, location);
        eventPublisher.publishEvent(event);
    }
}

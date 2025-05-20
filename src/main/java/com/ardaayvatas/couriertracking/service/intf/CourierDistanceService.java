package com.ardaayvatas.couriertracking.service.intf;

import com.ardaayvatas.couriertracking.data.response.ResponseCourierDistance;

public interface CourierDistanceService {
    ResponseCourierDistance findByCourierId(Long id);
}

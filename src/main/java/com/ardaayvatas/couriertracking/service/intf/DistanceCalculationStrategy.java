package com.ardaayvatas.couriertracking.service.intf;

import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;

import java.util.List;

public interface DistanceCalculationStrategy {
    double calculateDistance(double lat1, double lng1, double lat2, double lng2);
    double calculateTotalDistance(List<CourierLocationDTO> locations);
}

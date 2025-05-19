package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.service.intf.DistanceCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculatorServiceImpl implements DistanceCalculationStrategy {
    @Override
    public double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        final int earthRadius = 6371000;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lngDistance = Math.toRadians(lng2 - lng1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}

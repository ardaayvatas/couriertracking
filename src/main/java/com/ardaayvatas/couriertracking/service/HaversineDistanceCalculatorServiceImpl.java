package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.service.intf.DistanceCalculationStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HaversineDistanceCalculatorServiceImpl implements DistanceCalculationStrategy {
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

    @Override
    public double calculateTotalDistance(List<CourierLocationDTO> locations) {
        double totalDistance = 0.0;

        if (locations == null || locations.size() < 2) {
            return 0;
        }

        for (int i = 1; i < locations.size(); i++) {
            CourierLocationDTO prev = locations.get(i - 1);
            CourierLocationDTO current = locations.get(i);

            totalDistance += calculateDistance(
                    prev.getLat(), prev.getLng(),
                    current.getLat(), current.getLng()
            );
        }
        return totalDistance;
    }

}

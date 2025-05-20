package com.ardaayvatas.couriertracking.batch.processor;

import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierDistanceDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierStoreEntranceDTO;
import com.ardaayvatas.couriertracking.service.HaversineDistanceCalculatorServiceImpl;
import com.ardaayvatas.couriertracking.service.intf.CourierLocationService;
import com.ardaayvatas.couriertracking.service.intf.CourierService;
import com.ardaayvatas.couriertracking.service.intf.CourierStoreEntranceService;
import com.ardaayvatas.couriertracking.service.intf.DistanceCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CourierDistanceProcessor {

    private final CourierService courierService;
    private final CourierLocationService courierLocationService;
    private final CourierStoreEntranceService courierStoreEntranceService;
    private final HaversineDistanceCalculatorServiceImpl haversineDistanceCalculatorService;


    @Bean
    public ItemProcessor<Long, CourierDistanceDTO> processor() {
        return courierId -> {
            CourierDTO courierDTO = courierService.findById(courierId);

            LocalDate targetDate = LocalDate.now().minusDays(1);
            LocalDateTime startDate = LocalDateTime.of(targetDate, LocalTime.MIN);
            LocalDateTime endDate = targetDate.atTime(LocalTime.MAX);

            List<CourierLocationDTO> locations = courierLocationService.findByCourierIdAndCreatedDateBetween(courierId, startDate, endDate);
            List<CourierStoreEntranceDTO> entrances = courierStoreEntranceService.findByCourierIdAndCreatedDateBetween(courierId, startDate, endDate);

            double totalDistance = calculateTotalDistance(haversineDistanceCalculatorService, locations);
            int entranceCount = entrances.size();

            return createCourierDistanceDTO(courierDTO, totalDistance, entranceCount);
        };
    }

    private CourierDistanceDTO createCourierDistanceDTO(CourierDTO courierDTO, double totalDistance, int entranceCount) {
        return CourierDistanceDTO.builder().
                courierDTO(courierDTO).
                storeEntranceCount(entranceCount).
                totalDistance(totalDistance).
                build();
    }

    private double calculateTotalDistance(DistanceCalculationStrategy distanceCalculationStrategy, List<CourierLocationDTO> locations) {
        return distanceCalculationStrategy.calculateTotalDistance(locations);
    }

}


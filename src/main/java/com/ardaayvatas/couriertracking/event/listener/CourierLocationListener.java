package com.ardaayvatas.couriertracking.event.listener;

import com.ardaayvatas.couriertracking.common.exception.ServiceCallException;
import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierStoreEntranceDTO;
import com.ardaayvatas.couriertracking.data.dto.StoreDTO;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.enumeration.ExceptionType;
import com.ardaayvatas.couriertracking.event.CourierLocationEvent;
import com.ardaayvatas.couriertracking.service.intf.CourierStoreEntranceService;
import com.ardaayvatas.couriertracking.service.intf.DistanceCalculationStrategy;
import com.ardaayvatas.couriertracking.service.intf.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CourierLocationListener {
    private final StoreService storeService;
    private final CourierStoreEntranceService courierStoreEntranceService;
    private final DistanceCalculationStrategy distanceCalculationStrategy;

    public CourierLocationListener(StoreService storeService,
                                   CourierStoreEntranceService courierStoreEntranceService,
                                   DistanceCalculationStrategy distanceCalculationStrategy) {
        this.distanceCalculationStrategy = distanceCalculationStrategy;
        this.storeService = storeService;
        this.courierStoreEntranceService = courierStoreEntranceService;
    }

    @Async
    @EventListener
    @Retryable(
            value = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    @Transactional
    public void onCourierLocationEvent(CourierLocationEvent event) {
        CourierLocationDTO location = event.getLocation();

        List<StoreDTO> storeDTOList = storeService.findAll();

        for (StoreDTO storeDTO : storeDTOList) {
            double distance = calculateDistance(location.getLat(), location.getLng(), storeDTO.getLat(), storeDTO.getLng());

            if (distance <= 100) {
                boolean reEntered = isReEntered(location.getCourierDTO().getId(),storeDTO.getId());

                if (!reEntered) {
                    courierStoreEntranceService.save(CourierMapper.INSTANCE.toCourierStoreEntrance(createCourierStoreEntranceDTO(location.getCourierDTO(),storeDTO)));
                }
            }
        }
    }

    private boolean isReEntered(Long courierId, Long storeId) {
        return courierStoreEntranceService.existsByCourierIdAndStoreIdAndCreatedDateAfter(courierId, storeId, LocalDateTime.now().minusMinutes(1));
    }

    private double calculateDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
        return distanceCalculationStrategy.calculateDistance(lat1, lng1, lat2, lng2);
    }

    @Recover
    public void recover(Exception e, CourierLocationEvent event) {
        throw new ServiceCallException(ExceptionType.EVENT_ERROR);
    }

    private CourierStoreEntranceDTO createCourierStoreEntranceDTO(CourierDTO courierDTO, StoreDTO storeDTO) {
        return CourierStoreEntranceDTO.builder()
                .courierDTO(courierDTO)
                .storeDTO(storeDTO)
                .createdDate(LocalDateTime.now())
                .build();
    }
}

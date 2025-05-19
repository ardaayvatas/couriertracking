package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.data.dao.model.CourierStoreEntrance;
import com.ardaayvatas.couriertracking.data.dao.repository.CourierStoreEntranceRepository;
import com.ardaayvatas.couriertracking.service.intf.CourierStoreEntranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CourierStoreEntranceServiceImpl implements CourierStoreEntranceService {
    private final CourierStoreEntranceRepository courierStoreEntranceRepository;

    @Override
    public Boolean existsByCourierIdAndStoreIdAndCreatedDateAfter(Long courierId, Long storeId, LocalDateTime createdDate) {
        return courierStoreEntranceRepository.existsByCourierIdAndStoreIdAndCreatedDateAfter(courierId, storeId, createdDate);
    }

    @Override
    public void save(CourierStoreEntrance courierStoreEntrance) {
        courierStoreEntranceRepository.save(courierStoreEntrance);
    }
}
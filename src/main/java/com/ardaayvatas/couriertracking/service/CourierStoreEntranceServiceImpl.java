package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.data.dao.model.CourierStoreEntrance;
import com.ardaayvatas.couriertracking.data.dao.repository.CourierStoreEntranceRepository;
import com.ardaayvatas.couriertracking.data.dto.CourierStoreEntranceDTO;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.service.intf.CourierStoreEntranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierStoreEntranceServiceImpl implements CourierStoreEntranceService {
    private final CourierStoreEntranceRepository courierStoreEntranceRepository;
    private final CourierMapper courierMapper;

    @Override
    public Boolean existsByCourierIdAndStoreIdAndCreatedDateAfter(Long courierId, Long storeId, LocalDateTime createdDate) {
        return courierStoreEntranceRepository.existsByCourierIdAndStoreIdAndCreatedDateAfter(courierId, storeId, createdDate);
    }

    @Override
    @Transactional
    public void save(CourierStoreEntrance courierStoreEntrance) {
        courierStoreEntranceRepository.save(courierStoreEntrance);
    }

    @Override
    public List<CourierStoreEntranceDTO> findByCourierIdAndCreatedDateBetween(Long courierId, LocalDateTime startDate, LocalDateTime endDate) {
        return courierMapper.toCourierStoreEntranceDTO(courierStoreEntranceRepository.findByCourierIdAndCreatedDateBetween(courierId, startDate, endDate));
    }
}
package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.data.dao.model.Store;
import com.ardaayvatas.couriertracking.data.dao.repository.StoreRepository;
import com.ardaayvatas.couriertracking.data.dto.StoreDTO;
import com.ardaayvatas.couriertracking.data.mapper.StoreMapper;
import com.ardaayvatas.couriertracking.service.intf.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    @Override
    public List<StoreDTO> findAll() {
        return StoreMapper.INSTANCE.toStoreDTOList(storeRepository.findAll());
    }

    @Override
    public void saveAll(List<Store> stores) {
        storeRepository.saveAll(stores);
    }
}

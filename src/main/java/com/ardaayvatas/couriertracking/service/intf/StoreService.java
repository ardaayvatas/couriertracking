package com.ardaayvatas.couriertracking.service.intf;

import com.ardaayvatas.couriertracking.data.dao.model.Store;
import com.ardaayvatas.couriertracking.data.dto.StoreDTO;

import java.util.List;

public interface StoreService {
    List<StoreDTO> findAll();
    void saveAll(List<Store> stores);
}

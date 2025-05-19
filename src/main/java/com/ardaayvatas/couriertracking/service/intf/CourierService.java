package com.ardaayvatas.couriertracking.service.intf;

import com.ardaayvatas.couriertracking.data.dao.model.Courier;
import com.ardaayvatas.couriertracking.data.dto.CourierDTO;

public interface CourierService {
    CourierDTO findById(Long id);
    Courier saveCourier(CourierDTO courierDTO);
    boolean deleteCourier(Long id);
    boolean existById(Long id);
}

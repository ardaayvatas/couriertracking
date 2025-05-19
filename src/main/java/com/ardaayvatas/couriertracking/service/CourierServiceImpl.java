package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.common.exception.BusinessException;
import com.ardaayvatas.couriertracking.data.dao.model.Courier;
import com.ardaayvatas.couriertracking.data.dao.repository.CourierRepository;
import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.enumeration.ExceptionType;
import com.ardaayvatas.couriertracking.service.intf.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    private final CourierMapper mapper = CourierMapper.INSTANCE;

    @Override
    public CourierDTO findById(Long id) {
        Optional<Courier> optionalCourier = courierRepository.findById(id);
        if (optionalCourier.isPresent()) {
            return CourierMapper.INSTANCE.toCourierDTO(optionalCourier.get());
        } else {
            throw new BusinessException(ExceptionType.COURIER_NOT_FOUND);
        }
    }

    @Override
    public Courier saveCourier(CourierDTO courierDTO) {
        return courierRepository.save(mapper.toCourier(courierDTO));
    }

    @Override
    public boolean deleteCourier(Long id) {
        findById(id);
        courierRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean existById(Long id) {
        return courierRepository.existsById(id);
    }
}

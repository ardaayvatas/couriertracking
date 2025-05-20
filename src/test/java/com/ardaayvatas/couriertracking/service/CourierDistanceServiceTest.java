package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.common.exception.BusinessException;
import com.ardaayvatas.couriertracking.data.dao.model.Courier;
import com.ardaayvatas.couriertracking.data.dao.model.CourierDistance;
import com.ardaayvatas.couriertracking.data.dao.repository.CourierDistanceRepository;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.data.response.ResponseCourierDistance;
import com.ardaayvatas.couriertracking.enumeration.ExceptionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierDistanceServiceTest {

    @InjectMocks
    private CourierDistanceServiceImpl courierDistanceService;

    @Mock
    private CourierDistanceRepository courierDistanceRepository;

    @Mock
    private CourierMapper courierMapper;

    @Test
    void testFindByCourierId_ReturnsResponseCourierDistance_WhenFound() {
        Long courierId = 1L;
        Courier courier = new Courier();
        courier.setId(courierId);

        CourierDistance entity = new CourierDistance();
        entity.setCourier(courier);
        entity.setTotalDistance(1000.0);
        entity.setStoreEntranceCount(3);

        ResponseCourierDistance response = new ResponseCourierDistance();
        response.setCourierId(courierId);
        response.setTotalDistance(1000.0);
        response.setStoreEntranceCount(3);

        when(courierDistanceRepository.findByCourierId(courierId)).thenReturn(Optional.of(entity));
        when(courierMapper.toResponseCourierDistance(entity)).thenReturn(response);
        ResponseCourierDistance result = courierDistanceService.findByCourierId(courierId);

        assertNotNull(result);
        assertEquals(1000.0, result.getTotalDistance());
        verify(courierDistanceRepository).findByCourierId(courierId);
        verify(courierMapper).toResponseCourierDistance(entity);
    }

    @Test
    void testFindByCourierId_ThrowsException_WhenNotFound() {
        Long courierId = 99L;
        when(courierDistanceRepository.findByCourierId(courierId)).thenReturn(Optional.empty());
        BusinessException exception = assertThrows(BusinessException.class, () -> courierDistanceService.findByCourierId(courierId));
        assertEquals(ExceptionType.COURIER_DISTANCE_ERROR, exception.getExceptionType());
        verify(courierDistanceRepository).findByCourierId(courierId);
        verifyNoInteractions(courierMapper);
    }
}

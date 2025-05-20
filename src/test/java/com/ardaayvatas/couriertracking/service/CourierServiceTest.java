package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.common.exception.BusinessException;
import com.ardaayvatas.couriertracking.data.dao.model.Courier;
import com.ardaayvatas.couriertracking.data.dao.repository.CourierRepository;
import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.enumeration.ExceptionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierServiceTest {

    @InjectMocks
    private CourierServiceImpl courierService;

    @Mock
    private CourierRepository courierRepository;

    @Test
    void testFindById_Success() {
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setName("TST");
        courier.setSurname("TST");
        when(courierRepository.findById(1L)).thenReturn(Optional.of(courier));
        CourierDTO result = courierService.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("TST", result.getName());
    }

    @Test
    void testFindById_NotFound_ThrowsException() {
        when(courierRepository.findById(999L)).thenReturn(Optional.empty());
        BusinessException exception = assertThrows(BusinessException.class, () -> courierService.findById(999L));
        assertEquals(ExceptionType.COURIER_NOT_FOUND, exception.getExceptionType());
    }

    @Test
    void testSaveCourier() {
        CourierDTO courierDTO = CourierDTO.builder().id(1L).name("TST").surname("TST").build();
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setName("TST");
        courier.setSurname("TST");
        when(courierService.saveCourier(any())).thenReturn(courier);
        Courier saved = courierService.saveCourier(courierDTO);
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }

    @Test
    void testDeleteCourier_Success() {
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setName("TST");
        courier.setSurname("TST");
        when(courierRepository.findById(1L)).thenReturn(Optional.of(courier));
        boolean result = courierService.deleteCourier(1L);
        assertTrue(result);
        verify(courierRepository).deleteById(1L);
    }

    @Test
    void testExistById() {
        when(courierRepository.existsById(1L)).thenReturn(true);
        boolean exists = courierService.existById(1L);
        assertTrue(exists);
        verify(courierRepository).existsById(1L);
    }
}

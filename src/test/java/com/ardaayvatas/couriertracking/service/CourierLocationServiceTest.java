package com.ardaayvatas.couriertracking.service;

import com.ardaayvatas.couriertracking.common.exception.BusinessException;
import com.ardaayvatas.couriertracking.data.dao.model.CourierLocation;
import com.ardaayvatas.couriertracking.data.dao.repository.CourierLocationRepository;
import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.enumeration.ExceptionType;
import com.ardaayvatas.couriertracking.event.publisher.CourierLocationPublisher;
import com.ardaayvatas.couriertracking.service.intf.CourierService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourierLocationServiceTest {

    @InjectMocks
    private CourierLocationServiceImpl courierLocationService;

    @Mock
    private CourierLocationRepository courierLocationRepository;

    @Mock
    private CourierMapper courierMapper;

    @Mock
    private CourierLocationPublisher courierLocationPublisher;

    @Mock
    private CourierService courierService;

    @Test
    void testCreateCourierLocation_Success() {
        CourierDTO courierDTO = CourierDTO.builder().id(1L).name("Test Courier").build();
        CourierLocationDTO courierLocationDTO = CourierLocationDTO.builder()
                .courierDTO(courierDTO)
                .lat(41.0)
                .lng(29.0)
                .build();

        CourierLocation courierLocationEntity = new CourierLocation();
        when(courierService.existById(1L)).thenReturn(true);
        when(courierMapper.toCourierLocation(courierLocationDTO)).thenReturn(courierLocationEntity);
        CourierLocationDTO result = courierLocationService.createCourierLocation(courierLocationDTO);
        assertNotNull(result);
        assertEquals(courierDTO.getId(), result.getCourierDTO().getId());
        verify(courierLocationRepository).save(courierLocationEntity);
        verify(courierLocationPublisher).publishLocationEvent(courierLocationDTO);
    }

    @Test
    void testCreateCourierLocation_ThrowsException_WhenCourierNotFound() {
        CourierDTO courierDTO = CourierDTO.builder().id(999L).build();
        CourierLocationDTO courierLocationDTO = CourierLocationDTO.builder().courierDTO(courierDTO).build();
        when(courierService.existById(999L)).thenReturn(false);
        BusinessException exception = assertThrows(BusinessException.class, () -> courierLocationService.createCourierLocation(courierLocationDTO));
        assertEquals(ExceptionType.COURIER_NOT_FOUND, exception.getExceptionType());
        verifyNoInteractions(courierLocationRepository);
        verifyNoInteractions(courierLocationPublisher);
    }

    @Test
    void testFindByCourierIdAndCreatedDateBetween_ReturnsList() {
        Long courierId = 1L;
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();
        List<CourierLocation> entities = List.of(new CourierLocation());
        List<CourierLocationDTO> dtos = List.of(CourierLocationDTO.builder().lat(1.0).lng(2.0).build());
        when(courierLocationRepository.findByCourierIdAndCreatedDateBetween(courierId, start, end)).thenReturn(entities);
        when(courierMapper.toCourierLocationDTO(entities)).thenReturn(dtos);
        List<CourierLocationDTO> result = courierLocationService.findByCourierIdAndCreatedDateBetween(courierId, start, end);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(courierLocationRepository).findByCourierIdAndCreatedDateBetween(courierId, start, end);
        verify(courierMapper).toCourierLocationDTO(entities);
    }
}

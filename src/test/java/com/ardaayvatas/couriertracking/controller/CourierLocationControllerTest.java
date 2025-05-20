package com.ardaayvatas.couriertracking.controller;

import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.dto.CourierLocationDTO;
import com.ardaayvatas.couriertracking.data.request.RequestCreateCourierLocation;
import com.ardaayvatas.couriertracking.service.intf.CourierLocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourierLocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierLocationService courierLocationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateCourierLocation() throws Exception {
        CourierDTO courierDTO = CourierDTO.builder().id(1L).build();

        CourierLocationDTO dto = CourierLocationDTO.builder()
                .courierDTO(courierDTO)
                .lat(41.01)
                .lng(29.02)
                .build();

        Mockito.when(courierLocationService.createCourierLocation(any())).thenReturn(dto);

        RequestCreateCourierLocation request = new RequestCreateCourierLocation();
        request.setCourierId(1L);
        request.setLat(41.01);
        request.setLng(29.02);

        mockMvc.perform(post("/api/courier-locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courierId", is(1)))
                .andExpect(jsonPath("$.lat", is(41.01)))
                .andExpect(jsonPath("$.lng", is(29.02)));
    }
}

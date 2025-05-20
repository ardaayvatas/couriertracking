package com.ardaayvatas.couriertracking.controller;

import com.ardaayvatas.couriertracking.data.dao.model.Courier;
import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.request.RequestCreateCourier;
import com.ardaayvatas.couriertracking.service.intf.CourierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierService courierService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateCourier() throws Exception {

        Courier courier = new Courier();
        courier.setId(1L);
        courier.setName("TST");
        courier.setSurname("TST");

        Mockito.when(courierService.saveCourier(any())).thenReturn(courier);

        RequestCreateCourier request = new RequestCreateCourier();
        request.setName("TST");
        request.setSurname("TST");

        mockMvc.perform(post("/api/couriers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("TST")))
                .andExpect(jsonPath("$.surname", is("TST")));
    }

    @Test
    void testGetCourierById() throws Exception {
        CourierDTO courierDTO = CourierDTO.builder()
                .id(1L)
                .name("TST")
                .surname("TST")
                .build();

        Mockito.when(courierService.findById(1L)).thenReturn(courierDTO);

        mockMvc.perform(get("/api/couriers/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("TST")))
                .andExpect(jsonPath("$.surname", is("TST")));
    }

    @Test
    void testDeleteCourier() throws Exception {
        Mockito.when(courierService.deleteCourier(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/couriers/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}

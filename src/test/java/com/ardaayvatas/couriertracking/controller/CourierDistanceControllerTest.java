package com.ardaayvatas.couriertracking.controller;

import com.ardaayvatas.couriertracking.data.response.ResponseCourierDistance;
import com.ardaayvatas.couriertracking.service.intf.CourierDistanceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourierDistanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourierDistanceService courierDistanceService;

    @Test
    void testGetCourierDistanceByCourierId() throws Exception {
        ResponseCourierDistance response = new ResponseCourierDistance();
        response.setCourierId(42L);
        response.setTotalDistance(7890.12);
        response.setStoreEntranceCount(7);

        Mockito.when(courierDistanceService.findByCourierId(42L)).thenReturn(response);

        mockMvc.perform(get("/api/courier-distance/42")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courierId", is(42)))
                .andExpect(jsonPath("$.totalDistance", is(7890.12)))
                .andExpect(jsonPath("$.storeEntranceCount", is(7)));
    }
}

package com.ardaayvatas.couriertracking.controller;

import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;

import com.ardaayvatas.couriertracking.data.request.RequestCreateCourierLocation;
import com.ardaayvatas.couriertracking.data.response.ResponseCreateCourierLocation;
import com.ardaayvatas.couriertracking.service.intf.CourierLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courier-locations")
@RequiredArgsConstructor
@Tag(name = "Courier Location API", description = "Manages couriers locations")
public class CourierLocationController {

    private final CourierLocationService courierLocationService;
    private static final CourierMapper mapper = CourierMapper.INSTANCE;

    @PostMapping
    @Operation(summary = "Logs locations")
    public ResponseEntity<ResponseCreateCourierLocation> createCourierLocation(@RequestBody RequestCreateCourierLocation requestCreateCourierLocation) {
        return ResponseEntity.ok(mapper.courierLocationDTOToResponseCreateCourierLocation(courierLocationService.createCourierLocation(mapper.requestCreateCourierLocationToCourierLocationDTO(requestCreateCourierLocation))));
    }

}
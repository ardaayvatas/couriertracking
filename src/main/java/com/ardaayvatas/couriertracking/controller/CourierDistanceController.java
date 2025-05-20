package com.ardaayvatas.couriertracking.controller;

import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.data.response.ResponseCourierDistance;
import com.ardaayvatas.couriertracking.service.intf.CourierDistanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courier-distance")
@RequiredArgsConstructor
@Tag(name = "Courier Distance API", description = "Manages courier distance operations")
public class CourierDistanceController {

    private final CourierDistanceService courierDistanceService;
    private static final CourierMapper mapper = CourierMapper.INSTANCE;

    @GetMapping("/{id}")
    @Operation(summary = "Get courier total distance by courier id")
    public ResponseEntity<ResponseCourierDistance> getCourierDistanceById(@PathVariable Long id) {
        ResponseCourierDistance responseCourierDistance = courierDistanceService.findByCourierId(id);
        return ResponseEntity.ok(responseCourierDistance);
    }

}

package com.ardaayvatas.couriertracking.controller;

import com.ardaayvatas.couriertracking.data.dao.model.Courier;
import com.ardaayvatas.couriertracking.data.dto.CourierDTO;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import com.ardaayvatas.couriertracking.data.request.RequestCreateCourier;
import com.ardaayvatas.couriertracking.service.intf.CourierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/couriers")
@RequiredArgsConstructor
@Tag(name = "Courier API", description = "Manages courier operations")
public class CourierController {

    private final CourierService courierService;
    private final CourierMapper mapper = CourierMapper.INSTANCE;

    @PostMapping
    @Operation(summary = "Create a new courier")
    public ResponseEntity<Courier> createCourier(@RequestBody RequestCreateCourier requestCreateCourier) {
        return ResponseEntity.ok(courierService.saveCourier(mapper.requestCreateCourierToCourier(requestCreateCourier)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get courier by id")
    public ResponseEntity<CourierDTO> getCourierById(@PathVariable Long id) {
        CourierDTO courierDTO = courierService.findById(id);
        return ResponseEntity.ok(courierDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete courier by id")
    public ResponseEntity<Boolean> deleteCourier(@PathVariable Long id) {
        return ResponseEntity.ok(courierService.deleteCourier(id));
    }
}
package com.ardaayvatas.couriertracking.initializer;

import com.ardaayvatas.couriertracking.data.dao.model.Store;
import com.ardaayvatas.couriertracking.service.intf.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class StoreDataLoader implements CommandLineRunner {

    private final StoreService storeService;
    private final ObjectMapper objectMapper;

    public StoreDataLoader(StoreService storeService) {
        this.storeService = storeService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        if (storeService.findAll().isEmpty()) {
            InputStream inputStream = new ClassPathResource("stores.json").getInputStream();
            List<Store> stores = Arrays.asList(objectMapper.readValue(inputStream, Store[].class));
            storeService.saveAll(stores);
        }
    }
}
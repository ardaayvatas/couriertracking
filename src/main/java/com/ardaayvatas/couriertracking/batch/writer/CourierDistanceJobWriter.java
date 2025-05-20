package com.ardaayvatas.couriertracking.batch.writer;

import com.ardaayvatas.couriertracking.data.dao.repository.CourierDistanceRepository;
import com.ardaayvatas.couriertracking.data.dto.CourierDistanceDTO;
import com.ardaayvatas.couriertracking.data.mapper.CourierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CourierDistanceJobWriter {

    private final CourierDistanceRepository courierDistanceRepository;
    private final CourierMapper courierMapper;

    @Bean
    public ItemWriter<CourierDistanceDTO> writer() {
        return (Chunk<? extends CourierDistanceDTO> items) -> {
            List<CourierDistanceDTO> dtoList = items.getItems().stream()
                    .map(CourierDistanceDTO.class::cast)
                    .toList();

            var entities = courierMapper.toCourierDistance(dtoList);
            courierDistanceRepository.saveAll(entities);
        };
    }
}

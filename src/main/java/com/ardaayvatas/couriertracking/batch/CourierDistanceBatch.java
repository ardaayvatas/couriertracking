package com.ardaayvatas.couriertracking.batch;

import com.ardaayvatas.couriertracking.data.dao.repository.CourierDistanceRepository;
import com.ardaayvatas.couriertracking.data.dto.CourierDistanceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class CourierDistanceBatch {

    private final CourierDistanceRepository courierDistanceRepository;

    @Value("${batch.courier-distance-batch.chunk-size:10}")
    private Integer chunkSize;

    @Bean
    public Step courierDistanceStep(JobRepository jobRepository,
                                    PlatformTransactionManager transactionManager,
                                    ItemReader<Long> courierIdReader,
                                    ItemProcessor<Long, CourierDistanceDTO> processor,
                                    ItemWriter<CourierDistanceDTO> writer) {

        return new StepBuilder("courierDistanceStep", jobRepository)
                .<Long, CourierDistanceDTO>chunk(chunkSize, transactionManager)
                .reader(courierIdReader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job courierDistanceJob(JobRepository jobRepository, Step courierDistanceStep, Step deleteCourierDistancesStep) {
        return new JobBuilder("courierDistanceJob", jobRepository)
                .start(deleteCourierDistancesStep)
                .next(courierDistanceStep)
                .build();
    }

    @Bean
    public Step deleteCourierDistancesStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("deleteCourierDistancesStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    courierDistanceRepository.deleteAllInBatch();
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

}

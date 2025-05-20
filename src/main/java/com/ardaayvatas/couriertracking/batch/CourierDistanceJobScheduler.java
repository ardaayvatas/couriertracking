package com.ardaayvatas.couriertracking.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CourierDistanceJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job courierDistanceJob;

    public CourierDistanceJobScheduler(JobLauncher jobLauncher, Job courierDistanceJob) {
        this.jobLauncher = jobLauncher;
        this.courierDistanceJob = courierDistanceJob;
    }

    @Scheduled(cron = "35 18 4 * * ?")
    public void runJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(courierDistanceJob, jobParameters);
    }
}

package com.wedul.wedul_timeline.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * batch scheduler
 *
 * @author wedul
 * @since 2019-07-12
 **/
@Configuration
@RequiredArgsConstructor
public class BatchScheduler {

    private final Job job;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "00 10 10,18 * * *")
    public void batchScheduler() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(job, new JobParametersBuilder().addDate("requestDate", new Date()).toJobParameters());
    }

}

package com.spring.batch.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledJob {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "stockControl")
    private Job job;

    @Scheduled(cron = "${batch.stockCron}")
    public void stockControlJob() throws Exception {
        log.info("stockControlJob start");
        jobLauncher.run(job, new JobParameters());
        log.info("stockControlJob end");
    }

}

package com.spring.batch.config;

import com.spring.batch.exception.InvalidPriceException;
import com.spring.batch.exception.InvalidQuantityException;
import com.spring.batch.model.Product;
import com.spring.batch.model.StockLog;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.InvalidNameException;
import java.sql.SQLException;

@Configuration
public class StockControlJobConfig {

    @Bean
    @Qualifier("stockControl")
    public Job stockControlJob(JobRepository jobRepository, Step step1) {

        return new JobBuilder("stockControl", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      RepositoryItemReader<Product> reader,
                      ItemProcessor<Product, StockLog> processor,
                      ItemWriter<StockLog> writer) {

        return new StepBuilder("step1", jobRepository)
                .<Product, StockLog>chunk(10000, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .retryLimit(3)
                .retry(DataAccessException.class)
                .retry(SQLException.class)
                .skipLimit(10000)
                .skip(InvalidPriceException.class)
                .skip(InvalidQuantityException.class)
                .noSkip(InvalidNameException.class)
                .build();
    }

}

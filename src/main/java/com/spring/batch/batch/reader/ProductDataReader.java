package com.spring.batch.batch.reader;

import com.spring.batch.model.Product;
import com.spring.batch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ProductDataReader {

    private final ProductRepository productRepository;

    @Bean
    @StepScope
    public RepositoryItemReader<Product> reader() {

        return new RepositoryItemReaderBuilder<Product>()
                .name("productDataReader")
                .repository(productRepository)
                .methodName("findAllBy")
                .pageSize(1000)
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();
    }

}

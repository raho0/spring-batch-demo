package com.spring.batch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Data
public class StockLog {

    @Id
    @UuidGenerator
    private String id;

    private String productId;
    private int productQuantity;
    private LocalDateTime checkDate;

}

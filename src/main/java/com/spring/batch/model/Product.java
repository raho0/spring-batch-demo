package com.spring.batch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
public class Product {

    @Id
    @UuidGenerator
    private String id;

    private String name;
    private String description;
    private double price;
    private int quantity;

}

package com.spring.batch.batch.processor;

import com.spring.batch.exception.InvalidPriceException;
import com.spring.batch.exception.InvalidQuantityException;
import com.spring.batch.exception.ProductNameException;
import com.spring.batch.model.Product;
import com.spring.batch.model.StockLog;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StockControlProcessor implements ItemProcessor<Product, StockLog> {

    @Override
    public StockLog process(Product item) throws Exception {

        validate(item);

        if (item.getQuantity() < 20) {
            StockLog stockLog = new StockLog();

            stockLog.setProductId(item.getId());
            stockLog.setProductQuantity(item.getQuantity());
            stockLog.setCheckDate(LocalDateTime.now());

            return stockLog;
        }

        return null;
    }

    private static void validate(Product item) {
        if (item.getQuantity() < 0)
            throw new InvalidQuantityException("Quantity must be greater than 0");

        if (item.getPrice() <= 0)
            throw new InvalidPriceException("Price must be greater than 0");

        if (item.getName() == null || item.getName().isEmpty())
            throw new ProductNameException("Name cannot be empty");
    }

}

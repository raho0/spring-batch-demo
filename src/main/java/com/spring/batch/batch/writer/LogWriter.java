package com.spring.batch.batch.writer;

import com.spring.batch.model.StockLog;
import com.spring.batch.repository.StockLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class LogWriter implements ItemWriter<StockLog> {

    private final StockLogRepository stockLogRepository;

    @Override
    public void write(Chunk<? extends StockLog> chunk) throws Exception {

        stockLogRepository.saveAll(chunk);
    }

}

package com.spring.batch.repository;

import com.spring.batch.model.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockLogRepository extends JpaRepository<StockLog, String> {

}

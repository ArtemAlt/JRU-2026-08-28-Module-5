package com.example.app.saga.clients;

import com.example.app.saga.dto.ReleaseStockRequest;
import com.example.app.saga.dto.ReserveStockRequest;
import com.example.app.saga.dto.ReserveStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "warehouse-service")
public interface WarehouseClient {
    @PostMapping("/stock/reserve")
    ReserveStockResponse reserve(@RequestHeader("X-Command-Id") String commandId,
                                 @RequestBody ReserveStockRequest request);

    @PostMapping("/stock/release")
    void release(@RequestHeader("X-Command-Id") String commandId,
                 @RequestBody ReleaseStockRequest request);
}
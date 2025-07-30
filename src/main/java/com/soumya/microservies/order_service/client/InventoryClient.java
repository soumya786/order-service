package com.soumya.microservies.order_service.client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@FeignClient(name = "inventory", url = "${inventory.url}")

public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    //@RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    @GetExchange("/api/inventory")
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    @Retry(name="inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
        // Log the error or handle it as needed
        log.info("Fallback method called for skuCode: {}, quantity: {}, error: {}", skuCode, quantity, throwable.getMessage());
        return false; // Default response when inventory service is unavailable
    }
}

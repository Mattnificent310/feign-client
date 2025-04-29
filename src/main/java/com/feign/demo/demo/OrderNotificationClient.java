package com.feign.demo.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "forwardingClient",
        url = "http://initial-placeholder",
        configuration = FeignClientConfig.class
)
public interface OrderNotificationClient {
    @PostMapping(consumes = "application/json", produces = "application/json", value = "/order/{recipientId}")
    OrderResponseDto post(@RequestHeader(value = "recipientId") String fspId,
                          @RequestBody OrderDto body);
}
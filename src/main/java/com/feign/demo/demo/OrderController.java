package com.feign.demo.demo;

import feign.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderNotificationClient orderNotificationClient;

    @PostMapping("/order")

    public OrderResponseDto sendOrderNotification(@RequestBody OrderDto orderDto) {
        return orderNotificationClient.post(orderDto.getRecipientId(), orderDto);
    }
}

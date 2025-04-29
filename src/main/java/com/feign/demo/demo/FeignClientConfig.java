package com.feign.demo.demo;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FeignClientConfig {
    private final ForwardingService forwardingUrlService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            if (!template.headers().containsKey("recipientId")) {
                log.error("No recipientId header found in request - request will be skipped");
                return;
            }

            String recipientId = template.headers().get("recipientId").iterator().next();
            forwardingUrlService.getForwardingUrl(recipientId).ifPresentOrElse(
                    url -> {
                        log.info("Setting URL for recipientId {}: {}", recipientId, url);
                        template.target(url);
                    },
                    () -> log.warn("No forwarding URL found for recipientId: {}", recipientId)
            );
        };
    }
}
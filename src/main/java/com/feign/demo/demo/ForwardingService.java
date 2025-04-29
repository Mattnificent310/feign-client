package com.feign.demo.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Service
public class ForwardingService {
    private final List<RecipientConfig> recipients;

    public ForwardingService(ForwardingProperties forwardingProperties) {
        this.recipients = forwardingProperties.getRecipients();
    }

    public Optional<String> getForwardingUrl(String recipientId) {
        return recipients.stream()
                .filter(recipient -> recipient.getRecipientId().equals(recipientId))
                .map(RecipientConfig::getUrl)
                .findFirst();
    }

    @ConfigurationProperties(prefix = "forwarding")
    @Data
    public static class ForwardingProperties {
        private List<RecipientConfig> recipients;
    }

    @Data
    public static class RecipientConfig {
        private String recipientId;
        private String url;
    }
}
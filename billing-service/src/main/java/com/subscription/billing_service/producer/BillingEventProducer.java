package com.subscription.billing_service.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillingEventProducer {

    private static final String TOPIC = "subscription.charge_due";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public BillingEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishSubscriptionChargeDue(UUID subscriptionId) {
        kafkaTemplate.send(TOPIC, subscriptionId.toString());
    }

}

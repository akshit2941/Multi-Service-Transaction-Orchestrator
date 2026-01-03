package com.subscription.orchestrator_service.kafka;

import com.subscription.orchestrator_service.service.BillingOrchestratorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubscriptionChargeListener {
    private final BillingOrchestratorService orchestratorService;

    public SubscriptionChargeListener(BillingOrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @KafkaListener(
            topics = "subscription.charge_due",
            groupId = "orchestrator-group"
    )
    public void handleSubscriptionCharge(String subscriptionId) {
        orchestratorService.startBillingWorkflow(UUID.fromString(subscriptionId));
    }
}

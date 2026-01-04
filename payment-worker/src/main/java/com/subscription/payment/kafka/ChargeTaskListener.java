package com.subscription.payment.kafka;

import com.subscription.payment.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChargeTaskListener {
    private PaymentService paymentService;

    public void handleChargeTask(String message){
        String[] parts = message.split(",");

        UUID taskId = UUID.fromString(parts[0]);
        UUID workflowId = UUID.fromString(parts[1]);
        UUID subscriptionId = UUID.fromString(parts[2]);

        paymentService.processCharge(taskId, workflowId, subscriptionId);
    }
}

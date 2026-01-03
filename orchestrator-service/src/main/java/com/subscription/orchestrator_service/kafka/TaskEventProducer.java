package com.subscription.orchestrator_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskEventProducer {

    private static final String TOPIC = "task.charge";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TaskEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishChargeTask(UUID taskId, UUID workflowId, UUID subscriptionId) {
        String message = taskId + "," + workflowId + "," + subscriptionId;
        kafkaTemplate.send(TOPIC, message);
    }
}

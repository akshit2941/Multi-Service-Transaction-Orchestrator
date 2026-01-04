package com.subscription.payment.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskResultProducer {
    private static final String TOPIC = "task.completed";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TaskResultProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishTaskCompleted(UUID taskId, UUID workflowId) {
        String message = taskId + "," + workflowId;
        kafkaTemplate.send(TOPIC, message);
    }
}

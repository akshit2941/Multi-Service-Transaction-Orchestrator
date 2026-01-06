package com.subscription.orchestrator_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WorkflowEventProducer {

    private static final String TOPIC = "workflow.completed";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WorkflowEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishWorkflowCompleted(UUID workflowId) {
        kafkaTemplate.send(TOPIC, workflowId.toString());
    }
}
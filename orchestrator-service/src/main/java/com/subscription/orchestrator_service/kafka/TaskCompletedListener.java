package com.subscription.orchestrator_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskCompletedListener {

    @KafkaListener(
            topics = "task.completed",
            groupId = "orchestrator-group"
    )
    public void handleTaskCompleted(String message){
        String[] parts = message.split(",");

        UUID taskId = UUID.fromString(parts[0]);
        UUID workflowId = UUID.fromString(parts[1]);
    }
}

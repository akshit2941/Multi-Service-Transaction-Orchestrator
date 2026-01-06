package com.subscription.orchestrator_service.service;

import com.subscription.orchestrator_service.kafka.WorkflowEventProducer;
import com.subscription.orchestrator_service.repository.TaskRepository;
import com.subscription.orchestrator_service.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkflowCompletionService {

    private final TaskRepository taskRepository;
    private final WorkflowRepository workflowRepository;
    private final WorkflowEventProducer workflowEventProducer;


    public void completedWorkflow(UUID taskId, UUID workflowId){
        taskRepository.findById(taskId).ifPresent(task -> {
            task.setStatus("SUCCESS");
            taskRepository.save(task);
        });

        workflowRepository.findById(workflowId).ifPresent(workflow -> {
            workflow.setStatus("COMPLETED");
            workflowRepository.save(workflow);
        });

        workflowEventProducer.publishWorkflowCompleted(workflowId);
    }


}

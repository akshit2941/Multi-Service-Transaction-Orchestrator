package com.subscription.orchestrator_service.service;

import com.subscription.orchestrator_service.entity.Task;
import com.subscription.orchestrator_service.entity.Workflow;
import com.subscription.orchestrator_service.kafka.TaskEventProducer;
import com.subscription.orchestrator_service.repository.TaskRepository;
import com.subscription.orchestrator_service.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillingOrchestratorService {

    private final WorkflowRepository workflowRepository;
    private final TaskRepository taskRepository;
    private final TaskEventProducer taskEventProducer;


    public void startBillingWorkflow(UUID subscriptionId){
        Workflow workflow = Workflow.builder()
                .type("BILLING")
                .entityId(subscriptionId)
                .status("RUNNING")
                .build();

        Workflow savedWorkflow = workflowRepository.save(workflow);


        Task task = Task.builder()
                .workflowId(savedWorkflow.getId())
                .taskType("CHARGE")
                .status("PENDING")
                .build();

        Task savedTask = taskRepository.save(task);

        taskEventProducer.publishChargeTask(
                savedTask.getId(),
                savedWorkflow.getId(),
                subscriptionId
        );
    }
}

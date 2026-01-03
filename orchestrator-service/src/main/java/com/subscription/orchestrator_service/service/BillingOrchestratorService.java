package com.subscription.orchestrator_service.service;

import com.subscription.orchestrator_service.entity.Task;
import com.subscription.orchestrator_service.entity.Workflow;
import com.subscription.orchestrator_service.kafka.TaskEventProducer;
import com.subscription.orchestrator_service.repository.TaskRepository;
import com.subscription.orchestrator_service.repository.WorkflowRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillingOrchestratorService {

    private WorkflowRepository workflowRepository;
    private TaskRepository taskRepository;
    private TaskEventProducer taskEventProducer;


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

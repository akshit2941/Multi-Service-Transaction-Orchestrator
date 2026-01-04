package com.subscription.payment.service;

import com.subscription.payment.entity.Transaction;
import com.subscription.payment.kafka.TaskResultProducer;
import com.subscription.payment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final TransactionRepository transactionRepository;
    private final TaskResultProducer taskResultProducer;

    public void processCharge(UUID taskId, UUID workflowId, UUID subscriptionId){

        Transaction txn = Transaction.builder()
                .subscriptionId(subscriptionId)
                .status("PENDING")
                .attemptNumber(1)
                .build();

        Transaction savedTxn = transactionRepository.save(txn);

        simulatePayment();

        savedTxn.setStatus("SUCCESS");
        transactionRepository.save(savedTxn);

        taskResultProducer.publishTaskCompleted(taskId, workflowId);
    }

    private void simulatePayment() {
        try {
            Thread.sleep(2000); // simulate gateway call
        } catch (InterruptedException ignored) {}
    }
}

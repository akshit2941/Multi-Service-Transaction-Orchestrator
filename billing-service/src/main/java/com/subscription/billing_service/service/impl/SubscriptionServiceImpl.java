package com.subscription.billing_service.service.impl;

import com.subscription.billing_service.dto.request.SubscriptionRequest;
import com.subscription.billing_service.entity.Subscription;
import com.subscription.billing_service.enums.SubscriptionStatus;
import com.subscription.billing_service.producer.BillingEventProducer;
import com.subscription.billing_service.service.ISubscriptionRepositoryService;
import com.subscription.billing_service.service.ISubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements ISubscriptionService {

    private final ISubscriptionRepositoryService subscriptionRepositoryService;
    private final BillingEventProducer billingEventProducer;

    @Override
    public void createSubscription(SubscriptionRequest request) {

        Subscription subscription = Subscription.builder()
                .userId(UUID.fromString(request.getUserId()))
                .planId(request.getPlanId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .nextBillingAt(LocalDateTime.now())
                .status(SubscriptionStatus.ACTIVE)
                .build();

        Subscription savedSubscription = subscriptionRepositoryService.save(subscription);

        billingEventProducer.publishSubscriptionChargeDue(savedSubscription.getId());


    }
}

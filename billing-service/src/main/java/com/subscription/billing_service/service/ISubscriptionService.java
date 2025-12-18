package com.subscription.billing_service.service;

import com.subscription.billing_service.dto.request.SubscriptionRequest;
import org.springframework.stereotype.Service;

@Service
public interface ISubscriptionService {
    void createSubscription(SubscriptionRequest request);
}

package com.subscription.billing_service.service;

import com.subscription.billing_service.dto.request.SubscriptionRequest;

public interface ISubscriptionService {
    void createSubscription(SubscriptionRequest request);
}

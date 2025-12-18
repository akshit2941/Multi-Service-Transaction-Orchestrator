package com.subscription.billing_service.service;

import com.subscription.billing_service.entity.Subscription;

public interface ISubscriptionRepositoryService {
    Subscription save(Subscription subscription);
}

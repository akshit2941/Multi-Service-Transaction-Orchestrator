package com.subscription.billing_service.service.impl;

import com.subscription.billing_service.entity.Subscription;
import com.subscription.billing_service.repositories.SubscriptionRepository;
import com.subscription.billing_service.service.ISubscriptionRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionRepositoryServiceImpl implements ISubscriptionRepositoryService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription save(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }
}

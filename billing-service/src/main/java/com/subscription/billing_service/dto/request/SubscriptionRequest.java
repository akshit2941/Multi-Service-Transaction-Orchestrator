package com.subscription.billing_service.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubscriptionRequest {
    private String userId;
    private String planId;
    private BigDecimal amount;
    private String currency;
}

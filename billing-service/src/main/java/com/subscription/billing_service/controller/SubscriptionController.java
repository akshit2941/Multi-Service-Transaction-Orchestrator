package com.subscription.billing_service.controller;

import com.subscription.billing_service.dto.request.SubscriptionRequest;
import com.subscription.billing_service.dto.response.ApiResponse;
import com.subscription.billing_service.service.ISubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private ISubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createSubscription(@RequestBody SubscriptionRequest request){
        subscriptionService.createSubscription(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<Void>builder()
                                .code(200)
                                .message("Subscription created successfully")
                                .data(null)
                                .build()
                );
    }
}

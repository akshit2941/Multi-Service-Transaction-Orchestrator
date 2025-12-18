package com.subscription.billing_service.repositories;

import com.subscription.billing_service.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefundRepository extends JpaRepository<Refund, UUID> {
}

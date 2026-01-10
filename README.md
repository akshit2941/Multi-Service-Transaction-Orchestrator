# Subscription Billing Orchestrator

A simple **event-driven backend system** built using **Spring Boot, Kafka, and PostgreSQL** to demonstrate how **subscription billing workflows** can be handled using microservices.

---

## What This Project Does

- Creates subscriptions via REST API
- Publishes billing events to Kafka
- Orchestrates billing workflows asynchronously
- Executes simulated payment processing
- Tracks workflow and task state in SQL
- Completes the billing flow using Kafka events

---

## Kafka Topics Used

| Topic Name | Description |
|-----------|-------------|
| `subscription.charge_due` | Triggered when a subscription needs to be billed |
| `task.charge` | Assigns a charge task to the payment worker |
| `task.completed` | Signals successful completion of a task |
| `workflow.completed` | Signals completion of a billing workflow |

---

## Tech Stack

- Java + Spring Boot  
- Apache Kafka  
- PostgreSQL  
- Docker  

---

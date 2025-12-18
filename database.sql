CREATE TABLE subscriptions
(
    id              UUID PRIMARY KEY,

    user_id         UUID      not null,
    plan_id         VARCHAR(100),

    amount          DECIMAL(10, 2),
    currency        VARCHAR(3),

    status          VARCHAR(20),
    -- ACTIVE, CANCELLED, SUSPENDED

    next_billing_at TIMESTAMP,

    created_at      TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE transactions
(
    id              UUID PRIMARY KEY,

    subscription_id UUID,

    amount          DECIMAL(10, 2),
    currency        VARCHAR(3),

    status          VARCHAR(20),
    -- PENDING, SUCCESS, FAILED, REFUNDED

    attempt_number  INT,
    gateway_ref     VARCHAR(100),

    created_at      TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE refunds
(
    id             UUID PRIMARY KEY,

    transaction_id UUID,

    amount         DECIMAL(10, 2),
    reason         VARCHAR(255),

    status         VARCHAR(20),
    -- REQUESTED, PROCESSING, COMPLETED, FAILED

    created_at     TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE workflows
(
    id         UUID PRIMARY KEY,

    type       VARCHAR(20),
    -- BILLING, REFUND

    entity_id  UUID,
    -- subscription_id or refund_id

    status     VARCHAR(20),
    -- RUNNING, COMPLETED, FAILED

    created_at TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tasks
(
    id            UUID PRIMARY KEY,

    workflow_id   UUID,

    task_type     VARCHAR(50),
    -- CHARGE, REFUND, NOTIFY

    status        VARCHAR(20),
    -- PENDING, RUNNING, SUCCESS, FAILED

    retry_count   INT                DEFAULT 0,
    error_message TEXT,

    created_at    TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP
);



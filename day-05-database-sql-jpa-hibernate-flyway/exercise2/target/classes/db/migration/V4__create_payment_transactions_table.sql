CREATE TABLE payment_transactions (
    id BIGSERIAL PRIMARY KEY,
    repayment_schedule_id BIGINT NOT NULL REFERENCES repayment_schedules(id),
    payment_reference VARCHAR(100) NOT NULL,
    paid_amount NUMERIC(15,2) NOT NULL,
    paid_at TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'SUCCESS',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);
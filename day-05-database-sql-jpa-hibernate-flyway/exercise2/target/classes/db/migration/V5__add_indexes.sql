CREATE INDEX idx_customers_nik ON customers(nik);
CREATE INDEX idx_customers_email ON customers(email);
CREATE INDEX idx_loan_applications_customer_id ON loan_applications(customer_id);
CREATE INDEX idx_loan_applications_status ON loan_applications(status);
CREATE INDEX idx_repayment_schedules_loan_application_id ON repayment_schedules(loan_application_id);
CREATE INDEX idx_repayment_schedules_status ON repayment_schedules(status);
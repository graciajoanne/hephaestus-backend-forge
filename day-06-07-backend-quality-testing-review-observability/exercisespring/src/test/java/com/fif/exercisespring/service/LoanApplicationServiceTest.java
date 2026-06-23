package com.fif.exercisespring.service;

import com.fif.exercisespring.dto.CreateCustomerRequest;
import com.fif.exercisespring.dto.CreateLoanApplicationRequest;
import com.fif.exercisespring.dto.LoanApplicationResponse;
import com.fif.exercisespring.exception.CustomerNotFoundException;
import com.fif.exercisespring.exception.ForbiddenException;
import com.fif.exercisespring.exception.LoanApplicationNotFoundException;
import com.fif.exercisespring.security.RoleValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanApplicationServiceTest {

    private LoanApplicationService loanApplicationService;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
        loanApplicationService = new LoanApplicationService();

        // Buat customer dummy untuk dipakai di test
        CreateCustomerRequest customerRequest = new CreateCustomerRequest();
        customerRequest.setFullName("Budi Santoso");
        customerRequest.setEmail("budi@mail.com");
        customerRequest.setPhoneNumber("08123456789");
        customerService.createCustomer(customerRequest);
    }

    @Test
    void should_create_loan_application_successfully() {
        // given
        CreateLoanApplicationRequest request = new CreateLoanApplicationRequest();
        request.setCustomerId(1L);
        request.setLoanAmount(5000000.0);
        request.setTenorMonth(12);
        request.setPurpose("Modal usaha");

        // when
        LoanApplicationResponse response = loanApplicationService
            .createLoanApplication(request);

        // then
        assertNotNull(response);
        assertEquals(1L, response.getCustomerId());
        assertEquals("SUBMITTED", response.getStatus());
        assertEquals("Modal usaha", response.getPurpose());
    }

    @Test
    void should_throw_not_found_when_customer_does_not_exist() {
        // given
        CreateLoanApplicationRequest request = new CreateLoanApplicationRequest();
        request.setCustomerId(999L);
        request.setLoanAmount(5000000.0);
        request.setTenorMonth(12);
        request.setPurpose("Modal usaha");

        // when & then
        assertThrows(CustomerNotFoundException.class,
                () -> loanApplicationService.createLoanApplication(request));
    }

    @Test
    void should_get_loan_application_by_id_successfully() {
        // given
        CreateLoanApplicationRequest request = new CreateLoanApplicationRequest();
        request.setCustomerId(1L);
        request.setLoanAmount(5000000.0);
        request.setTenorMonth(12);
        request.setPurpose("Modal usaha");
        LoanApplicationResponse created = loanApplicationService
            .createLoanApplication(request);

        // when
        LoanApplicationResponse response = loanApplicationService
            .getLoanApplication(created.getId());

        // then
        assertNotNull(response);
        assertEquals(created.getId(), response.getId());
    }

    @Test
    void should_throw_not_found_when_loan_application_does_not_exist() {
        // given
        Long nonExistentId = 999L;

        // when & then
        assertThrows(LoanApplicationNotFoundException.class,
                () -> loanApplicationService.getLoanApplication(nonExistentId));
    }

    @Test
    void should_approve_loan_when_user_is_approver() {
        // given
        CreateLoanApplicationRequest request = new CreateLoanApplicationRequest();
        request.setCustomerId(1L);
        request.setLoanAmount(5000000.0);
        request.setTenorMonth(12);
        request.setPurpose("Modal usaha");
        LoanApplicationResponse created = loanApplicationService.createLoanApplication(request);
        String approverRole = "APPROVER";

        // when
        LoanApplicationResponse response = loanApplicationService.approveLoanApplication(created.getId(), approverRole);

        // then
        assertNotNull(response);
        assertEquals("APPROVED", response.getStatus());
    }

    @Test
    void should_reject_loan_when_user_is_approver() {
        // given
        CreateLoanApplicationRequest request = new CreateLoanApplicationRequest();
        request.setCustomerId(1L);
        request.setLoanAmount(5000000.0);
        request.setTenorMonth(12);
        request.setPurpose("Modal usaha");
        LoanApplicationResponse created = loanApplicationService
            .createLoanApplication(request);
        String approverRole = "APPROVER";

        // when
        RoleValidator.hasAnyRole(approverRole, "ADMIN", "APPROVER");
        LoanApplicationResponse response = loanApplicationService.rejectLoanApplication(created.getId());

        // then
        assertNotNull(response);
        assertEquals("REJECTED", response.getStatus());
    }

    @Test
    void should_throw_forbidden_when_staff_tries_to_approve_loan() {
        // given
        String staffRole = "STAFF";

        // when & then
        ForbiddenException exception = assertThrows(ForbiddenException.class,
                () -> RoleValidator.hasAnyRole(staffRole, "ADMIN", "APPROVER"));

        assertNotNull(exception.getMessage());
    }

    @Test
    void should_throw_forbidden_when_staff_tries_to_reject_loan() {
        // given
        String staffRole = "STAFF";

        // when & then
        assertThrows(ForbiddenException.class,
                () -> RoleValidator.hasAnyRole(staffRole, "ADMIN", "APPROVER"));
    }
}
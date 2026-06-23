package com.fif.exercisespring.service;

import com.fif.exercisespring.dto.CreateCustomerRequest;
import com.fif.exercisespring.dto.CustomerResponse;
import com.fif.exercisespring.exception.CustomerNotFoundException;
import com.fif.exercisespring.exception.ForbiddenException;
import com.fif.exercisespring.security.RoleValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//Menguji business logic CustomerService. Karena kita ingin memastikan login bekerja sesuai requirement tanpa harus menjalankan aplikasi secara penuh.
/* @Test yg harus dibuat:
1. `should_create_customer_successfully`
2. `should_get_customer_by_id_successfully`
3. `should_throw_not_found_when_customer_does_not_exist`
4. `should_return_all_customers`
5. `should_not_allow_approver_to_create_customer`
 */
class CustomerServiceTest {

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    @Test
    void should_create_customer_successfully() {
        // given
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setFullName("Budi Santoso");
        request.setEmail("budi@mail.com");
        request.setPhoneNumber("08123456789");
        // when
        CustomerResponse response = customerService.createCustomer(request);
        // then
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("Budi Santoso", response.getFullName());
        assertEquals("budi@mail.com", response.getEmail());
    }

    @Test
    void should_get_customer_by_id_successfully() {
        // given
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setFullName("Budi Santoso");
        request.setEmail("budi@mail.com");
        request.setPhoneNumber("08123456789");
        CustomerResponse created = customerService.createCustomer(request);
        // when
        CustomerResponse response = customerService.getCustomer(created.getId());
        // then
        assertNotNull(response);
        assertEquals(created.getId(), response.getId());
        assertEquals("Budi Santoso", response.getFullName());
    }

    @Test
    void should_throw_not_found_when_customer_does_not_exist() {
        // given
        Long nonExistentId = 999L;
        // when & then
        assertThrows(CustomerNotFoundException.class,() -> customerService.getCustomer(nonExistentId));
    }

    @Test
    void should_return_all_customers() {
        // given
        CreateCustomerRequest request1 = new CreateCustomerRequest();
        request1.setFullName("Budi Santoso");
        request1.setEmail("budi@mail.com");
        request1.setPhoneNumber("08123456789");

        CreateCustomerRequest request2 = new CreateCustomerRequest();
        request2.setFullName("Ani Wijaya");
        request2.setEmail("ani@mail.com");
        request2.setPhoneNumber("08987654321");

        customerService.createCustomer(request1);
        customerService.createCustomer(request2);

        // when
        List<CustomerResponse> responses = customerService.getAllCustomers();

        // then
        assertNotNull(responses);
        assertEquals(2, responses.size());
    }

    @Test
    void should_not_allow_approver_to_create_customer() {
        // given
        String approverRole = "APPROVER";
        // when & then
        assertThrows(ForbiddenException.class, () -> RoleValidator.hasAnyRole(approverRole, "ADMIN", "STAFF"));
    }
}
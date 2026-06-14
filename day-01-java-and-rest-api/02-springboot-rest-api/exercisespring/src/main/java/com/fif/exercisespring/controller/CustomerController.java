package com.fif.exercisespring.controller;

import com.fif.exercisespring.dto.CreateCustomerRequest;
import com.fif.exercisespring.dto.CustomerResponse;
import com.fif.exercisespring.dto.UpdateCustomerRequest;
import com.fif.exercisespring.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        CustomerResponse response = customerService.getCustomer(id);
        if (response == null) {return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // @GetMapping
    // public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    //     return ResponseEntity.ok(customerService.getAllCustomers()
    //     );
    // }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(@RequestParam(required = false) String name) {
        if (name != null) {
            return ResponseEntity.ok(customerService.searchCustomers(name));
        }
        return ResponseEntity.ok(customerService.getAllCustomers()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest request) {
        CustomerResponse response = customerService.updateCustomer(id, request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
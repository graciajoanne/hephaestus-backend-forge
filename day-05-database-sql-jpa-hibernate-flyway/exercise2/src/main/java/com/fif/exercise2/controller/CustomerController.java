package com.fif.exercise2.controller;

import com.fif.exercise2.dto.ApiResponse;
import com.fif.exercise2.dto.CreateCustomerRequest;
import com.fif.exercise2.dto.CustomerResponse;
import com.fif.exercise2.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request) {
        CustomerResponse data = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Customer created successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(
            @PathVariable Long id) {
        CustomerResponse data = customerService.getCustomerById(id);
        return ResponseEntity.ok(ApiResponse.success("Customer retrieved successfully", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomers() {
        List<CustomerResponse> data = customerService.getAllCustomers();
        return ResponseEntity.ok(ApiResponse.success("Customers retrieved successfully", data));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> searchByName(
            @RequestParam String name) {
        List<CustomerResponse> data = customerService.searchByName(name);
        return ResponseEntity.ok(ApiResponse.success("Customers retrieved successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<com.fif.exercise2.dto.ApiResponse<Void>> deleteCustomer(
            @PathVariable Long id) {
        customerService.softDeleteCustomer(id);
        return ResponseEntity.ok(com.fif.exercise2.dto.ApiResponse
            .success("Customer deleted successfully", null));
    }
}
package com.fif.exercisespring.controller;

import com.fif.exercisespring.dto.CreateCustomerRequest;
import com.fif.exercisespring.dto.CustomerResponse;
import com.fif.exercisespring.dto.PatchCustomerRequest;
import com.fif.exercisespring.dto.UpdateCustomerRequest;
import com.fif.exercisespring.exception.CustomerNotFoundException;
import com.fif.exercisespring.service.AuthService;
import com.fif.exercisespring.service.CustomerService;
import com.fif.exercisespring.security.AuthUtil;
import com.fif.exercisespring.security.RoleValidator;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer API",description = "Customer Management API")
public class CustomerController {
    private final CustomerService customerService;
    private final AuthService authService;
    
    @Operation(summary = "Create Customer")
    @ApiResponse(responseCode = "201", description = "Customer created")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestHeader("Authorization") String authorization,@Valid @RequestBody CreateCustomerRequest request) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role,"ADMIN","STAFF");
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    
    @Operation(summary = "Get Customer By Id")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestHeader(value = "Authorization", required = false) String authorization, @PathVariable Long id) throws CustomerNotFoundException{
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role,"ADMIN","STAFF","APPROVER", "MANAGER");
        CustomerResponse response = customerService.getCustomer(id);
        return ResponseEntity.ok(response);
    }

    // @GetMapping
    // public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
    //     return ResponseEntity.ok(customerService.getAllCustomers()
    //     );
    // }

    @Operation(summary = "Get All Customers")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(@RequestHeader("Authorization") String authorization, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role,"ADMIN","STAFF","APPROVER","MANAGER");
        if (email != null && !email.isBlank()) {
            return ResponseEntity.ok(customerService.searchCustomersByEmail(email));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(customerService.searchCustomers(name));
        }
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Operation(summary = "Update Customer")
    @ApiResponse(responseCode = "200", description = "Customer updated")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestHeader("Authorization") String authorization, @PathVariable Long id, @Valid @RequestBody UpdateCustomerRequest request) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "STAFF");
        CustomerResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "Delete Customer")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@RequestHeader("Authorization") String authorization, @PathVariable Long id) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "STAFF");
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Patch Customer")
    @ApiResponse(responseCode = "200", description = "Customer patched")
    @ApiResponse(responseCode = "404",description = "Customer not found")
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> patchCustomer(@RequestHeader("Authorization") String authorization, @PathVariable Long id,@RequestBody PatchCustomerRequest request) {
        String token = AuthUtil.extractToken(authorization);
        String role = authService.getRoleByToken(token);
        RoleValidator.hasAnyRole(role, "ADMIN", "STAFF");
        CustomerResponse response = customerService.patchCustomer(id, request);
        return ResponseEntity.ok(response);
    }
}
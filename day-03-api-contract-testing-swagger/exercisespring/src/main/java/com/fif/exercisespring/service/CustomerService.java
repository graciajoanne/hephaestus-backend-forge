package com.fif.exercisespring.service;

import com.fif.exercisespring.dto.CreateCustomerRequest;
import com.fif.exercisespring.dto.CustomerResponse;
import com.fif.exercisespring.dto.PatchCustomerRequest;
import com.fif.exercisespring.dto.UpdateCustomerRequest;
import com.fif.exercisespring.exception.CustomerNotFoundException;
import com.fif.exercisespring.model.Customer;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CustomerService {
    private final Map<Long, Customer> customers = new HashMap<>();
    private Long sequence = 1L;


    public List<CustomerResponse> getAllCustomers() {
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customers.values()) {
            responses.add(buildCustomerRespons(customer));
        }
        return responses;
    }

    public List<CustomerResponse> searchCustomersByEmail(String email) {
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customers.values()) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                responses.add(buildCustomerRespons(customer));
            }
        }
        return responses;
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request) {

        ZonedDateTime now = ZonedDateTime.now();

        Customer customer = new Customer(sequence,request.getFullName(),request.getEmail(),request.getPhoneNumber(), now, now);

        customers.put(sequence, customer);
        sequence++;

        return buildCustomerRespons(customer);
    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customers.get(id);

        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        return buildCustomerRespons(customer);
    }

    private CustomerResponse buildCustomerRespons(Customer customer) {
        return new CustomerResponse(customer.getId(),customer.getFullName(),customer.getEmail(),customer.getPhoneNumber(), customer.getCreatedAt(), customer.getUpdatedAt());
    }

    public CustomerResponse updateCustomer(Long id, UpdateCustomerRequest request) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setUpdatedAt(ZonedDateTime.now());
        return buildCustomerRespons(customer);
    }

    public boolean deleteCustomer(Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        customers.remove(id);
        return true;
    }

    public List<CustomerResponse> searchCustomers(String name) {
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customers.values()) {
            if (customer.getFullName().toLowerCase().contains(name.toLowerCase())) {
                responses.add(buildCustomerRespons(customer));}}
        return responses;
    }

    


    public CustomerResponse patchCustomer(Long id,PatchCustomerRequest request) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }

        if (request.getFullName() != null) {
            customer.setFullName(request.getFullName());
        }

        if (request.getEmail() != null) {
            customer.setEmail(request.getEmail());
        }

        if (request.getPhoneNumber() != null) {
            customer.setPhoneNumber(request.getPhoneNumber());
        }
        customer.setUpdatedAt(ZonedDateTime.now());
        return buildCustomerRespons(customer);
    }
    
}
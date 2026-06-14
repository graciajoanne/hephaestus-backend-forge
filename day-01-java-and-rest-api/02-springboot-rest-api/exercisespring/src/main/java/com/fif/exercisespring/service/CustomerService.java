package com.fif.exercisespring.service;

import com.fif.exercisespring.dto.CreateCustomerRequest;
import com.fif.exercisespring.dto.CustomerResponse;
import com.fif.exercisespring.dto.UpdateCustomerRequest;
import com.fif.exercisespring.model.Customer;
import org.springframework.stereotype.Service;

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

    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(sequence,request.getFullName(),request.getEmail(),request.getPhoneNumber());
        customers.put(sequence, customer);
        sequence++;
        return buildCustomerRespons(customer);
    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            return null;
        }
        return buildCustomerRespons(customer);
    }

    private CustomerResponse buildCustomerRespons(Customer customer) {
        return new CustomerResponse(customer.getId(),customer.getFullName(),customer.getEmail(),customer.getPhoneNumber());
    }

    public CustomerResponse updateCustomer(Long id,UpdateCustomerRequest request) {
        Customer customer = customers.get(id);
        if (customer == null) {
            return null;
        }
        customer.setFullName(request.getFullName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        return buildCustomerRespons(customer);
    }

    public boolean deleteCustomer(Long id) {Customer customer = customers.get(id);
        if (customer == null) {
            return false;
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
}
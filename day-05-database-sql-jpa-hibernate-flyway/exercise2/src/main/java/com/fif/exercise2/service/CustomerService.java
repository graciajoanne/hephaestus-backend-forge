package com.fif.exercise2.service;

import com.fif.exercise2.dto.CreateCustomerRequest;
import com.fif.exercise2.dto.CustomerResponse;
import com.fif.exercise2.entity.CustomerEntity;
import com.fif.exercise2.exception.CustomerNotFoundException;
import com.fif.exercise2.exception.DuplicateCustomerException;
import com.fif.exercise2.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        if (customerRepository.existsByNik(request.getNik())) {
            throw new DuplicateCustomerException("NIK already exists: " + request.getNik());
        }
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateCustomerException("Email already exists: " + request.getEmail());
        }

        CustomerEntity entity = new CustomerEntity();
        entity.setFullName(request.getFullName());
        entity.setNik(request.getNik());
        entity.setEmail(request.getEmail());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setCreatedAt(ZonedDateTime.now());
        entity.setUpdatedAt(ZonedDateTime.now());

        CustomerEntity saved = customerRepository.save(entity);
        return buildResponse(saved);
    }

    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long id) {
        CustomerEntity entity = customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException(id));
        return buildResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> searchByName(String name) {
        return customerRepository.findByFullNameContainingIgnoreCase(name)
            .stream()
            .map(this::buildResponse)
            .collect(Collectors.toList());
    }

    private CustomerResponse buildResponse(CustomerEntity entity) {
        CustomerResponse response = new CustomerResponse();
        response.setId(entity.getId());
        response.setFullName(entity.getFullName());
        response.setNik(entity.getNik());
        response.setEmail(entity.getEmail());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }

    @Transactional
    public void softDeleteCustomer(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setDeletedAt(ZonedDateTime.now());
        customerRepository.save(customer);
    }
}
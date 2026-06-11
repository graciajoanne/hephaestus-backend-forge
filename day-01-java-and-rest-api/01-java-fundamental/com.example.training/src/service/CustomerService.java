package service;

import java.util.*;

import model.Customer;

public class CustomerService {
    private Map<Long, Customer> customerStorage = new HashMap<>();
    private Long sequence = 1L;
    
    public Customer createCustomer(String fullName, String email, String phoneNumber) {
        try{
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Nama harus diisi!");
        }

        Customer customer = new Customer(sequence,fullName,email,phoneNumber);

        customerStorage.put(sequence, customer);
        sequence++;

        return customer;
    } catch(IllegalArgumentException e){
        System.out.println("Error: " + e.getMessage());
        return null;
    }
    }

    public Customer getCustomerById(Long id) {
        return customerStorage.get(id);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerStorage.values());
    }

    public void updateCustomer(Long id, String fullName, String email, String phoneNumber) {
        Customer customer = customerStorage.get(id);
        if (customer != null) {
            customer.setFullName(fullName);
            customer.setEmail(email);
            customer.setPhoneNumber(phoneNumber);
        }
    }
    public void deleteCustomer(Long id) {
        customerStorage.remove(id);
    }


}


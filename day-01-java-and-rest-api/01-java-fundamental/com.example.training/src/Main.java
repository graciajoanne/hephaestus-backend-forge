import java.util.List;

import model.Customer;
import service.CustomerService;

public class Main {
    public static void main(String[] args) throws Exception {
        CustomerService customerService = new CustomerService();
        customerService.createCustomer("Budi Santoso", "budi@mail.com", "08123456789");
        customerService.createCustomer("Siti Aminah", "siti@mail.com", "08987654321");
        
        System.out.println("All Customers:");

        List<Customer> customers = customerService.getAllCustomers();

        for (Customer customer : customers) {
            System.out.println(customer.getId() + " - "+ customer.getFullName() + " - "+ customer.getEmail() + " - "+ customer.getPhoneNumber());
        }

        Customer customer = customerService.getCustomerById(1L);

        System.out.println("\nCustomer Detail:");
        System.out.println(customer.getDisplayName());
    }
}

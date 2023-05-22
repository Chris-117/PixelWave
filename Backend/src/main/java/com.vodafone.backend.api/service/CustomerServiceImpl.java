package com.vodafone.backend.api.service;

import com.vodafone.backend.api.domain.Customer;
import com.vodafone.backend.api.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean exists(String email) {
        return customerRepository.existsByEmail(email);
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setSurname(updatedCustomer.getSurname());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setAdmin(updatedCustomer.isAdmin());
            existingCustomer.setCardNumber(updatedCustomer.getCardNumber());
            existingCustomer.setExpiry(updatedCustomer.getExpiry());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            existingCustomer.setPostcode(updatedCustomer.getPostcode());
            return customerRepository.save(existingCustomer);
        } else {
            return null;
        }
    }

    public boolean deleteCustomer(int id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
            return true;
        } else {
            return false;
        }
    }
}

package com.vodafone.backend.api.service;

import com.vodafone.backend.api.domain.Customer;
import com.vodafone.backend.api.repo.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    // Load user by username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find customer by email in the repository
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            // Throw an exception if the customer is not found
            throw new UsernameNotFoundException("User not found");
        }

        // Create a list of granted authorities
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (customer.isAdmin()) {
            // Add ROLE_ADMIN authority if the customer is an admin
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            // Add ROLE_USER authority if the customer is a regular user
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        // Create and return a User object with the customer's email, password, and authorities
        return new User(customer.getEmail(), customer.getPassword(), authorities);
    }
}

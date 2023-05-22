package com.vodafone.backend.api.controller;

import com.vodafone.backend.api.domain.AuthResponse;
import com.vodafone.backend.api.domain.Customer;
import com.vodafone.backend.api.service.CustomerServiceImpl;
import com.vodafone.backend.api.service.EmailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerServiceImpl customerService;
    private final EmailService emailService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // Dependency injection for CustomerServiceImpl and EmailService
    @Autowired
    public CustomerController(CustomerServiceImpl customerService, EmailService emailService) {
        this.customerService = customerService;
        this.emailService = emailService;
    }

    @Autowired
    public PasswordEncoder passwordEncoder;

    // Register a new user/customer
    @PostMapping("/signUp")
    public ResponseEntity<Customer> registerNewUser(@RequestBody Customer customer) throws Exception {
        try {
            if (customerService.exists(customer.getEmail())) { // Check if the account already exists
                // Throw an exception if the account already exists
                throw new Exception("Account already exists");
            }
            if (customer.getPassword() == null) {
                throw new Exception("Password is required");
            }
            customer.setPassword(passwordEncoder.encode(customer.getPassword())); // Validate the password and encode it using BCryptPasswordEncoder
            String subject = "Welcome!";
            String text = "Your account has been created, " + customer.getName() + customer.getSurname();
            emailService.sendEmail(customer.getEmail(), subject, text); // Send a welcome email to the user/customer
            return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED); // Add the customer to the database and return the ResponseEntity
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Handle exceptions and return appropriate HttpStatus

        }
    }

    // Log in a customer
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginCustomer(@RequestBody Map<String, String> credentials) {
        // Retrieve the email and password from the request body
        String email = credentials.get("email");
        String password = credentials.get("password");
        if (customerService.exists(email)) { // Check if the customer exists in the database
            Customer customer = customerService.findByEmail(email);

            if (!passwordEncoder.matches(password, customer.getPassword())) { // Compare the provided password with the stored hashed password using BCryptPasswordEncoder
                // Return an HTTP UNAUTHORIZED response if the login credentials are invalid
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            // Generate the authorization token
            String token = generateAuthToken(customer.getEmail());

            AuthResponse authResponse = new AuthResponse(token); // Create an AuthResponse object containing the token
            return new ResponseEntity<>(authResponse, HttpStatus.OK); // Return the AuthResponse with an HTTP OK response
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);// Return an HTTP UNAUTHORIZED response if the customer does not exist

    }

    private String generateAuthToken(String email) {
        // Generate an authorization token for the given email
        Customer customer = customerService.findByEmail(email);
        // Set the expiration time for the token
        Instant expirationTime = Instant.now().plus(Duration.ofHours(2));

        // add the claims of the token
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("exp", expirationTime.toEpochMilli());
        claims.put("id", customer.getCustomerId());
        claims.put("admin", customer.isAdmin());
        claims.put("email", customer.getEmail());
        claims.put("name" , customer.getName());
        claims.put("surname", customer.getSurname());
        claims.put("address", customer.getAddress());
        claims.put("postcode", customer.getPostcode());
        claims.put("cardNumber", customer.getCardNumber());
        claims.put("expiry", customer.getExpiry());

        // Generate the token

        return Jwts.builder()
          .setClaims(claims)
          .compact();
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

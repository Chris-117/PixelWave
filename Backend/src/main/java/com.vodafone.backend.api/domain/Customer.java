package com.vodafone.backend.api.domain;

import com.vodafone.backend.api.repo.CustomerRepository;
import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "The email should be a valid email address")
    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    @NotBlank(message = "The email cannot be empty or missing.")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "admin", nullable = false)
    private boolean admin;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiry")
    private String expiry;

    @Column(name = "address")
    private String address;

    @Column(name = "postcode")
    private String postcode;

    // Default constructor
    public Customer() {
    }

    // Parameterized constructor
    public Customer(String name, String surname, String email, boolean admin, String cardNumber, String expiry,
                    String address, String postcode, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.admin = admin;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.address = address;
        this.postcode = postcode;
        this.password = password;
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

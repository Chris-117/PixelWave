package com.vodafone.backend.api.repo;

import com.vodafone.backend.api.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);

    boolean existsByEmail(String email);
}

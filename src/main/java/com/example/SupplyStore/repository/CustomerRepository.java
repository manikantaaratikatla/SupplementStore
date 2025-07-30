package com.example.SupplyStore.repository;

import com.example.SupplyStore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
    Customer findByCustomerId(Long customerId); // Added for fetching customer by ID
}

package com.project.Task1.Customer.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByEmail(String email);
}
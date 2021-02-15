package com.project.Task1.Customer.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalCustomerEntityRepository extends JpaRepository<NormalCustomerEntity, Integer> {

    NormalCustomerEntity findByEmailAndPassword(String email,String pass);
    NormalCustomerEntity findByEmail(String email);
}
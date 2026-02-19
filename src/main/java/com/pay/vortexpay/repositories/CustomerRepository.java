package com.pay.vortexpay.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.vortexpay.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    
}

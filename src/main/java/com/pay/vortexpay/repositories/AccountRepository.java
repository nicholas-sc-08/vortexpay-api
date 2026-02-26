package com.pay.vortexpay.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pay.vortexpay.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{
    public Optional<Account> findAccountByAccountNumber(String accountNumber);
    public Optional<Account> findByCustomerId(UUID id);
}

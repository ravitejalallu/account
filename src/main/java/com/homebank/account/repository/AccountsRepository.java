package com.homebank.account.repository;

import com.homebank.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account,Long> {
    Optional<Account> findById(Long Id);
}

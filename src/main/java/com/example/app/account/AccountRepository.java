package com.example.app.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByOwner(String owner);
    List<Account> findByBalanceGreaterThan(BigDecimal balance);
    List<Account> findByOwnerContainsIgnoreCase(String owner);
    List<Account> findByBalanceBetween(BigDecimal min, BigDecimal max);
    boolean existsByOwner(String owner);
}

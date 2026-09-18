package com.example.app.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
public class BankTransferService {
    private final AccountRepository repository;

    public BankTransferService(AccountRepository accountRepository) {
        this.repository = accountRepository;
    }

    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        System.out.println("Transfer from: " + fromId + " to: " + toId + " amount: " + amount);
        Account from = repository.findById(fromId).orElseThrow();
        Account to = repository.findById(toId).orElseThrow();

        System.out.println("Before transfer balance from: " + from.getBalance() + " to: " + to.getBalance());

        from.setBalance(from.getBalance().subtract(amount));
        repository.save(from);
        System.out.println(" Processed from: " + amount);

//        if(amount.intValue() > 10000) {
//            throw new AmountToLarge();
//        }

        to.setBalance(to.getBalance().add(amount));
        repository.save(to);
        System.out.println("Transfer completed");
    }

    @Transactional
    public void somemessage(Long fromId, Long toId, BigDecimal amount) {
        transfer(fromId, toId, amount);
    }
}

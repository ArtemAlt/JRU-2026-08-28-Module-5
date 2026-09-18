package com.example.app;

import com.example.app.account.Account;
import com.example.app.account.AccountRepository;
import com.example.app.account.BankTransferService;
import com.example.app.service.GreetingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class Main implements CommandLineRunner {
    private final BankTransferService bankTransferService;
    private final AccountRepository accountRepository;

    public Main(BankTransferService bankTransferService, AccountRepository accountRepository) {
        this.bankTransferService = bankTransferService;
        this.accountRepository = accountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Account from = new Account(null, "Alice", new BigDecimal(1000));
        Account to = new Account(null, "Bob", new BigDecimal(500));
        accountRepository.save(from);
        accountRepository.save(to);

        System.out.println("-------START----------");
        System.out.println("From balance: " + from.getBalance());
        System.out.println("To balance: " + to.getBalance());
        System.out.println("Total balance: " + from.getBalance().add(to.getBalance()));

//        try {
//            bankTransferService.transfer(from.getId(), to.getId(), new BigDecimal(10001));
//        } catch (Exception e) {
//            System.out.println("Error: " +  e.getMessage());
//        }

        bankTransferService.somemessage(from.getId(), to.getId(), new BigDecimal(100));
        System.out.println("---------PROCESSED--------");

        Account fromAfter = accountRepository.findById(from.getId()).orElseThrow();
        Account toAfter = accountRepository.findById(to.getId()).orElseThrow();

        System.out.println("Processed balance from: " + fromAfter.getBalance());
        System.out.println("Processed balance to: " + toAfter.getBalance());
        System.out.println("Total processed balance: " + fromAfter.getBalance().add(toAfter.getBalance()));
        System.out.println("-------END----------");
    }
}

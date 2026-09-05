package com.example.app.quoter;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {
    @InjectRandom(min = 2, max = 6)
    private int repeat;
    private final String quote = "Ill be back";

    public TerminatorQuoter() {
        System.out.println("Stage 1 Repeat: " + repeat);
    }

    @PostConstruct
    public void init() {
        System.out.println("Stage 2 Repeat: " + repeat);
    }

    @Override
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("message = " + quote);
        }
    }

    @PostProxy
     public void warmQuote() {
         System.out.println("Stage 3 Repeat: " + repeat);
         System.out.println("Warm Quote: " + quote);
     }

     @PreDestroy
     public void destroy() {
        System.out.println("Destroy Repeat: " + repeat);
     }
}

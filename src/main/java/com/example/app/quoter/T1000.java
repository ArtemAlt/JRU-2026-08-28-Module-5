package com.example.app.quoter;

public class T1000 extends TerminatorQuoter implements Quoter {

    @Override
    @PostProxy
    public void sayQuote() {
        System.out.println("Где Джон Конор!!!");
    }
}

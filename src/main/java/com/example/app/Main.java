package com.example.app;

import com.example.app.controller.UserController;
import com.example.app.many.MyClass1;
import com.example.app.many.MyClass2;
import com.example.app.quoter.TerminatorQuoter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    //IoC
    public static void main(String[] args) {
        System.out.println("======START========");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        UserController bean = context.getBean(UserController.class);
//        bean.start();
        TerminatorQuoter bean = context.getBean(TerminatorQuoter.class);// interface
        bean.sayQuote();
        System.out.println("======END========");

    }
}

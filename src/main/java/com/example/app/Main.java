package com.example.app;

import com.example.app.service.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    //IoC
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        System.out.println("======START========");
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        UserController bean = context.getBean(UserController.class);
//        bean.start();
//        TerminatorQuoter bean = context.getBean(TerminatorQuoter.class);// interface
//        bean.sayQuote();
//        CommonService authorizationService = context.getBean(CommonService.class);
//        Boolean dfgdff = authorizationService.auth("dfgdff");
//        System.out.println(dfgdff);
//        UserController bean = context.getBean(UserController.class);
//        bean.start();
        GreetingService greetingService = context.getBean(GreetingService.class);
        String greet = greetingService.greet();
        System.out.println(greet);

        System.out.println("======END========");

    }
}
/*
Join point
Pointcut
Advice
Aspect
 */
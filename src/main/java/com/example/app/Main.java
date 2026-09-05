package com.example.app;

import com.example.app.quoter.TerminatorQuoter;
import com.example.app.service.AuthorizationService;
import com.example.app.service.CommonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    //IoC
    public static void main(String[] args) {
        System.out.println("======START========");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        UserController bean = context.getBean(UserController.class);
//        bean.start();
//        TerminatorQuoter bean = context.getBean(TerminatorQuoter.class);// interface
//        bean.sayQuote();
        CommonService authorizationService = context.getBean(CommonService.class);
        Boolean dfgdff = authorizationService.auth("dfgdff");
        System.out.println(dfgdff);

        System.out.println("======END========");

    }
}

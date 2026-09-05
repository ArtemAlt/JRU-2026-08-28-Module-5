package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class CommonService {
    @Autowired
    private final AuthorizationService authorizationService;

    public CommonService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }
    public Boolean auth(String name) {
        System.out.println("Common service auth");
        return authorizationService.auth(name);
    }
}

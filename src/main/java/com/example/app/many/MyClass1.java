package com.example.app.many;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyClass1 {

    private final MyClass2 myClass2;
    private final MyClass3 myClass3;
    private final MyClass4 myClass4;

    @Autowired
    public MyClass1(MyClass2 myClass2, MyClass3 myClass3, MyClass4 myClass4) {
        this.myClass2 = myClass2;
        this.myClass3 = myClass3;
        this.myClass4 = myClass4;
    }
}

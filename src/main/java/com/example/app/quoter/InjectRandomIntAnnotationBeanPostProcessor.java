package com.example.app.quoter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

@Component
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            InjectRandom injectRandom = field.getAnnotation(InjectRandom.class);
            if (injectRandom != null) {
                field.setAccessible(true);
                int min = injectRandom.min();
                int max = injectRandom.max();
                int value = min + new Random().nextInt(max - min);
                ReflectionUtils.setField(field, bean, value);
//                System.out.println("Set random value to " + value);
            }
        }
//        System.out.println("BPP Before Initialization " + bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("BPP After Initialization" + bean.getClass());
        return bean;
    }
}


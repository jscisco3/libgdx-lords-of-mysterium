package com.jscisco.lom.application;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ServiceLocator implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static ApplicationContext getContext() {
        return ctx;
    }

    public static <T> T getBean(Class<T> t) {
        return ctx.getBean(t);
    }

}

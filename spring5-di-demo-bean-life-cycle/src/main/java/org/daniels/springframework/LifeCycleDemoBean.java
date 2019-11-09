package org.daniels.springframework;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class LifeCycleDemoBean implements BeanNameAware,
        BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    public LifeCycleDemoBean() {
        System.out.println("#1# I'm in the LifeCycleBean Constructor");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("#2# My Bean Name is: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("#3# Bean Factory has been set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("#4# Application context has been set");
    }

    public void beforeInit(){
        System.out.println("#5# - Before Init Preinitialization - Called by Bean Post Processor");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("#6# The Post Construct annotated method has been called");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("#7# The LifeCycleBean has its properties set!");

    }

    public void afterInit(){
        System.out.println("#8# - After init - Post Preinitialization- called by Bean Post Processor");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("#9# The Predestroy annotated method has been called");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("#10# The Lifecycle bean has been terminated");

    }
}

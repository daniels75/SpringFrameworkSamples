package org.daniels.springboot.samples.initalization;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanExample implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(getClass().getSimpleName() + " has been initialized.");
    }

    public String info() {
        return "Simple message from: " + getClass().getSimpleName();
    }
}

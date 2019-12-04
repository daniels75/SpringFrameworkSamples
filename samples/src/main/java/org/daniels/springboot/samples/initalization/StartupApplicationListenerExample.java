package org.daniels.springboot.samples.initalization;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListenerExample implements
        ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(getClass().getSimpleName() + " has been initialized.");
    }

    public String info() {
        return "Simple message from: " + getClass().getSimpleName();
    }
}

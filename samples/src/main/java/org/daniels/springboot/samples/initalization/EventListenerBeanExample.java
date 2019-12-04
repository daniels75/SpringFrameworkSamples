package org.daniels.springboot.samples.initalization;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerBeanExample {

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(getClass().getSimpleName() + " has been initialized.");
    }

    public String info() {
        return "Simple message from: " + getClass().getSimpleName();
    }
}

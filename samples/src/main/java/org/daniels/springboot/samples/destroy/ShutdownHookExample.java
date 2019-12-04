package org.daniels.springboot.samples.destroy;

import org.springframework.stereotype.Component;

@Component
public class ShutdownHookExample {

    public void destroy() {
        System.out.println(getClass().getSimpleName() + " has been destroyed");
    }

    public String info() {
        return "Simple message from: " + getClass().getSimpleName();
    }

}

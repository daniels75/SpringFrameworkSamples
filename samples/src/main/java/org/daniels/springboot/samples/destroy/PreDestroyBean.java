package org.daniels.springboot.samples.destroy;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class PreDestroyBean {

    @PreDestroy
    public void destroy(){
        System.out.println(this.getClass().getSimpleName() + " has been destroyed");
    }

    public String info() {
        return "Simple message from: " + this.getClass().getSimpleName();
    }
}

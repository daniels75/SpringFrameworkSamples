package org.daniels.springboot.samples.destroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class DisposableBeanExample implements DisposableBean {

    @Override
    public void destroy() throws  Exception{
        System.out.println(getClass().getSimpleName() + " has been destroyed");
    }

    public String info() {
        return "Simple message from: " + getClass().getSimpleName();
    }
}

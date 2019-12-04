package org.daniels.springboot.samples;

import org.daniels.springboot.samples.destroy.DisposableBeanExample;
import org.daniels.springboot.samples.destroy.PreDestroyBean;
import org.daniels.springboot.samples.destroy.ShutdownHookExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SamplesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SamplesApplication.class, args);

        System.out.println("---------------------------------------");
        PreDestroyBean preDestroyBean = (PreDestroyBean)context.getBean("preDestroyBean");
        System.out.println(preDestroyBean.info());
        System.out.println("---------------------------------------");
        DisposableBeanExample disposableBeanExample = (DisposableBeanExample)context.getBean("disposableBeanExample");
        System.out.println(disposableBeanExample.info());
        System.out.println("---------------------------------------");
        ShutdownHookExample shutdownHookExample = (ShutdownHookExample)context.getBean("shutdownHookExample");
        System.out.println(shutdownHookExample.info());
        System.out.println("---------------------------------------");


    }

}

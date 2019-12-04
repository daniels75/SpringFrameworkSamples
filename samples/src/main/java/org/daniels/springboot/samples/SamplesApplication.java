package org.daniels.springboot.samples;

import org.daniels.springboot.samples.destroy.PreDestroyBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SamplesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SamplesApplication.class, args);

        PreDestroyBean preDestroyBean = (PreDestroyBean)context.getBean("preDestroyBean");
        System.out.println(preDestroyBean.info());
    }

}

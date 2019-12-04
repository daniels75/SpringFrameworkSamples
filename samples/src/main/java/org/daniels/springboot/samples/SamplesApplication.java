package org.daniels.springboot.samples;

import org.daniels.springboot.samples.destroy.DisposableBeanExample;
import org.daniels.springboot.samples.destroy.PreDestroyBean;
import org.daniels.springboot.samples.destroy.ShutdownHookExample;
import org.daniels.springboot.samples.initalization.EventListenerBeanExample;
import org.daniels.springboot.samples.initalization.InitializingBeanExample;
import org.daniels.springboot.samples.initalization.PostConstructBeanExample;
import org.daniels.springboot.samples.initalization.StartupApplicationListenerExample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SamplesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SamplesApplication.class, args);

         // destroy
        destroyExamples(context);
        // initialization
        initializaionExamples(context);


    }

    private static void initializaionExamples(ApplicationContext context) {
        // init
        PostConstructBeanExample postConstructBeanExample = (PostConstructBeanExample)context.getBean("postConstructBeanExample");
        System.out.println(postConstructBeanExample.info());
        System.out.println("---------------------------------------");

        InitializingBeanExample initializingBeanExample = (InitializingBeanExample)context.getBean("initializingBeanExample");
        System.out.println(initializingBeanExample.info());
        System.out.println("---------------------------------------");

        StartupApplicationListenerExample startupApplicationListenerExample = (StartupApplicationListenerExample)context.getBean("startupApplicationListenerExample");
        System.out.println(startupApplicationListenerExample.info());
        System.out.println("---------------------------------------");

        EventListenerBeanExample eventListenerBeanExample = (EventListenerBeanExample)context.getBean("eventListenerBeanExample");
        System.out.println(eventListenerBeanExample.info());
        System.out.println("---------------------------------------");
    }

    private static void destroyExamples(ApplicationContext context) {
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

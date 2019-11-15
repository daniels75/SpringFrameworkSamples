package org.daniels.spring.didemo;

import org.daniels.spring.didemo.controllers.ConstructorInjectedController;
import org.daniels.spring.didemo.controllers.PropertyInjectedController;
import org.daniels.spring.didemo.controllers.SetterInjectedController;
import org.daniels.spring.didemo.controllers.SimpleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages =
        {
                "org.daniels.spring.didemo.services",
                "org.daniels.spring.didemo.controllers"
        })
public class DiDemoApplication {

    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);
        SimpleController simpleController  = (SimpleController)ctx.getBean("simpleController");
        simpleController.hello();

        System.out.println(simpleController.hello());
        System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
        System.out.println(ctx.getBean(SetterInjectedController.class).sayHello());
        System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());
    }

}

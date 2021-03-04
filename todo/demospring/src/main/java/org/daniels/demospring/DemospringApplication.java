package org.daniels.demospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemospringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemospringApplication.class, args);

        System.out.println(run.getBean(CurrencyComponent.class).currency());
        System.out.println(run.getBean(Info.class).info());
    }

    @Bean
    public Info createInfo() {
        return new Info();
    }


}

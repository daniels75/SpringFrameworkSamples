package org.daniels.springboot.samples.configurationproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConfigurationPropertiesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ConfigurationPropertiesApplication.class, args);

        ConfigProperties configProperties = (ConfigProperties)context.getBean("configProperties");

        System.out.println(configProperties.getHostName());
    }


}

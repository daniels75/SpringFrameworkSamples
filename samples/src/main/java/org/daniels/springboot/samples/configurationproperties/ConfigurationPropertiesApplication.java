package org.daniels.springboot.samples.configurationproperties;

import org.daniels.springboot.samples.destroy.DisposableBeanExample;
import org.daniels.springboot.samples.destroy.PreDestroyBean;
import org.daniels.springboot.samples.destroy.ShutdownHookExample;
import org.daniels.springboot.samples.initalization.EventListenerBeanExample;
import org.daniels.springboot.samples.initalization.InitializingBeanExample;
import org.daniels.springboot.samples.initalization.PostConstructBeanExample;
import org.daniels.springboot.samples.initalization.StartupApplicationListenerExample;
import org.springframework.beans.factory.annotation.Autowired;
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

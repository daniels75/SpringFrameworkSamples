package org.daniels.spring.demo.factory.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration()
public class PropertyConfig {

    @Value("db.username")
    private String username;
    @Value("db.password")
    private String password;
    @Value("url")
    private String url;

    public static PropertySourcesPlaceholderConfigurer configurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }
}

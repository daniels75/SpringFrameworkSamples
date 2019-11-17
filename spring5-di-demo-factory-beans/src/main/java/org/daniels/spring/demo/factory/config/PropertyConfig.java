package org.daniels.spring.demo.factory.config;

import org.daniels.spring.demo.factory.example.FakeDatasource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:datasource.properties")
public class PropertyConfig {

    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;

    @Bean
    public FakeDatasource fakeDatasource() {
        FakeDatasource fakeDbSource = new FakeDatasource();
        fakeDbSource.setUsername(username);
        fakeDbSource.setPassword(password);
        fakeDbSource.setUrl(url);
        return fakeDbSource;
    }

    public static PropertySourcesPlaceholderConfigurer configurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }
}

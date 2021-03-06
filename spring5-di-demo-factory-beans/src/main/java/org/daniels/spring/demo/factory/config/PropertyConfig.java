package org.daniels.spring.demo.factory.config;

import org.daniels.spring.demo.factory.example.FakeDatasource;
import org.daniels.spring.demo.factory.example.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
// 1st option from files
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})

// 2nd option from files
//@PropertySources({
//        @PropertySource("classpath:datasource.properties"),
//        @PropertySource("classpath:jms.properties")
//})

// 3rd option from application.properties
public class PropertyConfig {

    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;

    @Value("${jms.username}")
    private String jmsUsername;
    @Value("${jms.password}")
    private String jmsPassword;
    @Value("${jms.url}")
    private String jmsUrl;

//    @Autowired
//    Environment env;

    @Bean
    public FakeDatasource fakeDatasource() {
        FakeDatasource fakeDbSource = new FakeDatasource();
        fakeDbSource.setUsername(username);
        fakeDbSource.setPassword(password);
        fakeDbSource.setUrl(url);
        //fakeDbSource.setUrlFromEnv(env.getProperty("urlEnv"));
        return fakeDbSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUsername(jmsUsername);
        fakeJmsBroker.setPassword(jmsPassword);
        fakeJmsBroker.setUrl(jmsUrl);
        return fakeJmsBroker;
    }

    public static PropertySourcesPlaceholderConfigurer configurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }
}

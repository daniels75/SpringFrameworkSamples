package org.daniels.spring.resttemplate.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SimpleConfiguration {

    @Bean
    @Qualifier("default-rest-template")
    public RestTemplate createDefaultRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier("exception-rest-template")
    public RestTemplate createRestTemplateWithExceptionHandler() {
        return new RestTemplate();
    }
}

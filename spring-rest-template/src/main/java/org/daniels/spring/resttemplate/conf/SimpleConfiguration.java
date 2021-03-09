package org.daniels.spring.resttemplate.conf;

import org.daniels.spring.resttemplate.component.RestResponseErrorHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SimpleConfiguration {

    private final RestResponseErrorHandler restResponseErrorHandler;

    public SimpleConfiguration(RestResponseErrorHandler restResponseErrorHandler) {
        this.restResponseErrorHandler = restResponseErrorHandler;
    }

    @Bean
    @Qualifier("default-rest-template")
    public RestTemplate createDefaultRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier("exception-rest-template")
    public RestTemplate createRestTemplateWithExceptionHandler() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        builder.errorHandler(restResponseErrorHandler);

        return builder.build();
    }
}

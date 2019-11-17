package org.daniels.spring.joke.jokeapp.configuration;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class ChuckNorrisConfiguration {

    @Bean
    public ChuckNorrisQuotes createChuckNorrisQuotes(){
        return new ChuckNorrisQuotes();
    }
}

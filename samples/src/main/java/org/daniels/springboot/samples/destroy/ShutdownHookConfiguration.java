package org.daniels.springboot.samples.destroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShutdownHookConfiguration {

    @Bean(destroyMethod = "destroy")
    public ShutdownHookExample initializeBean3() {
        return new ShutdownHookExample();
    }
}

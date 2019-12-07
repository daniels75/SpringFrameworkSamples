package org.daniels.spring.spelexpressionexamples;

import org.daniels.spring.spelexpressionexamples.examples.SpelCollections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpelExpressionExamplesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpelExpressionExamplesApplication.class, args);
        SpelCollections spelCollections = (SpelCollections) context.getBean("spelCollections");

        System.out.println(spelCollections);
    }

}

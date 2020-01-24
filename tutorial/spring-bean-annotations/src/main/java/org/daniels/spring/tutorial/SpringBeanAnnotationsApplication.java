package org.daniels.spring.tutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBeanAnnotationsApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBeanAnnotationsApplication.class);

	public static void main(String[] args) {
		log.info("Spring bean annotations example");
		SpringApplication.run(SpringBeanAnnotationsApplication.class, args);

	}

}

package org.daniels.spring.sso.ui.second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class SpringSsoUiSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSsoUiSecondApplication.class, args);
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

}

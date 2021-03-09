package org.daniels.spring.resttemplate.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.daniels.spring.resttemplate.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "exception")
@Slf4j
public class ExceptionRest {
    private static final String baseUrl = "http://localhost:8093";

    @Qualifier("default-rest-template")
    @Autowired
    private RestTemplate defaultRestTemplate;

    @Qualifier("exception-rest-template")
    @Autowired
    private RestTemplate exceptionRestTemplate;

    @GetMapping(path = "/{key}", produces= MediaType.APPLICATION_JSON_VALUE)
    public String key(@PathVariable Integer key) {
        log.info("REST exception/key: {}", key);

        // call to anotherKey
        final String pathUrl = baseUrl  + "exception/{prefix}/{key}";

        ParameterizedTypeReference<HashMap<Integer, Car>> responseType =
                new ParameterizedTypeReference<HashMap<Integer, Car>>() {};

        ResponseEntity<HashMap<Integer, Car>> response =
                defaultRestTemplate
                .exchange(pathUrl,
                        HttpMethod.GET,
                        HttpEntity.EMPTY,
                        responseType,
                        "another",
                        key);

        return "key response: " + response.getBody().get(key);
    }

    @GetMapping(path = "/ex/{key}", produces= MediaType.APPLICATION_JSON_VALUE)
    public String keyEx(@PathVariable String key) {
        log.info("REST exception/ex/key: {}", key);

        // call to anotherKey
        final String pathUrl = baseUrl  + "exception/ex/{prefix}/{key}";

        ParameterizedTypeReference<HashMap<Integer, Car>> responseType =
                new ParameterizedTypeReference<HashMap<Integer, Car>>() {};

        ResponseEntity<HashMap<Integer, Car>> response =
                exceptionRestTemplate
                        .exchange(pathUrl,
                                HttpMethod.GET,
                                HttpEntity.EMPTY,
                                responseType,
                                "another",
                                key);

        return "ex/key response: " + response.getBody().get(key);
    }

    @GetMapping(value = "/another/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, Car> anotherKey(@PathVariable Integer key) {
        log.info("REST exception/another/key: {}", key);
        final Car car = Car.create(key, "simpleCar");

        return Collections.singletonMap(key, car);
    }
}

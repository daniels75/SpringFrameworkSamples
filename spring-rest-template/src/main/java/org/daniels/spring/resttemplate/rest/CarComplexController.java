package org.daniels.spring.resttemplate.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.daniels.spring.resttemplate.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping(path = "/complex")
@Slf4j
public class CarComplexController {

    private static final Map<String, Car> simpleMap = Maps.newHashMap();

    @Autowired
    RestTemplate restTemplate;

    private final String baseUrl = "http://localhost:8093";

    @PostMapping(path = "/{key}", produces= MediaType.APPLICATION_JSON_VALUE)
    public Car put(@PathVariable String key, @RequestBody Car car) {
        log.info("put key: {}, car: {}", key, car);
        logJson(car);
        return simpleMap.put(key, car);
    }

    @GetMapping(value = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car get(@PathVariable String key) {
        log.info("get key: {}, car: {}", key, simpleMap.get(key));
        return simpleMap.get(key);
    }

    @PostMapping(path = "/template/{key}", produces= MediaType.APPLICATION_JSON_VALUE)
    public String putViaRestTemplate(@PathVariable String key, @RequestBody Car car) {
        log.info("putViaRestTemplate key: {}, car: {}", key, car);
        logJson(car);

        String pathUrl = baseUrl  + "/complex/" + key;
        log.info("putViaRestTemplate - put with path: {}", pathUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Car> request = new HttpEntity<Car>(car, headers);

        Object result = restTemplate.postForEntity(pathUrl, car, Car.class);


        //Object result = restTemplate.postForEntity(pathUrl, request, Car.class);


        return "Added Car via REST Template";
    }

    private void logJson(Object obj) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(obj);
            log.info("Json object: {}", json);
        } catch (Exception e) {
            log.error("Cannot serialize object to json.", e);
        }

    }
}

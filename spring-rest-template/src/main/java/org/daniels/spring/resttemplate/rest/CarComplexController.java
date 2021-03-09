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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/complex")
//@AllArgsConstructor
@Slf4j
public class CarComplexController {

    private static final Map<String, Car> simpleMap = Maps.newHashMap();
    private static final Map<String, Map<String, Car>> complexMap = Maps.newHashMap();
    private static final String baseUrl = "http://localhost:8093";

    private final RestTemplate restTemplate;

    public CarComplexController(@Qualifier("default-rest-template") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(path = "/{key}", produces= MediaType.APPLICATION_JSON_VALUE)
    public Car post(@PathVariable String key, @RequestBody Car car) {
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

        String pathUrl = baseUrl  + "/{suffix}/{key}";
        log.info("putViaRestTemplate - put with path: {}", pathUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Car> request = new HttpEntity<Car>(car, headers);

        Object result = restTemplate.postForEntity(pathUrl,
                car,
                Car.class,
                "complex",
                key);


        HttpEntity<Car> request2 = new HttpEntity<>(car);
        ResponseEntity<Car> response = restTemplate
                .exchange(pathUrl, HttpMethod.POST, request, Car.class,
                        "complex",
                        key);

        //Object result = restTemplate.postForEntity(pathUrl, request, Car.class);


        return "Added Car via REST Template";
    }

    @PostMapping(path = "/map/{key}", produces= MediaType.APPLICATION_JSON_VALUE)
    public String postMap(@PathVariable String key, @RequestBody Map<String, Car> mapCar) {
        log.info("postMap key: {}, car: {}", key, mapCar);
        logJson(mapCar);
        return "added map";
    }

    @GetMapping(value = "/map/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Car> getMap(@PathVariable String key) {
        Car car = Car.create(10, "simpleCar");
        Map<String, Car>  mapCar = Collections.singletonMap("12345", car);
        logJson(mapCar);
        log.info("get key: {}, mapCar: {}", key, mapCar);
        return mapCar;
    }

    @GetMapping(path = "/map/template/{key}", produces= MediaType.APPLICATION_JSON_VALUE)
    public String getMapComplexTemplate(@PathVariable String key) {
        // call to getMapComplex
        String pathUrl = baseUrl  + "/{suffix}/map/complex/{key}";

        ParameterizedTypeReference<HashMap<String, Car>> responseType =
                new ParameterizedTypeReference<HashMap<String, Car>>() {};

        ResponseEntity<HashMap<String, Car>> response1 = restTemplate
                .exchange(pathUrl, HttpMethod.GET, HttpEntity.EMPTY, responseType,
                        "complex", key);
        log.info("getMapComplexTemplate - key:{}, result: {} ", key, response1.getBody().get(key));
        /*
        ResponseEntity response2 = restTemplate
                .getForEntity(pathUrl, Object.class,
                        "complex", key);
        */

        return "getMapComplexTemplate";
    }

    @GetMapping(value = "/map/complex/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Car> getMapComplex(@PathVariable String key) {
        Car car = Car.create(10, "simpleCar");
        Map<String, Car>  mapCar = Collections.singletonMap(key, car);
        complexMap.put(key, mapCar);

        logJson(mapCar);

        log.info("getMapTemplate key: {}, mapCar: {}", key, complexMap.get(key));

        return complexMap.get(key);
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

package org.daniels.spring.resttemplate.rest;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/cars")
@Slf4j
public class CarController {

    private static final Map<String, String> simpleMap = Maps.newHashMap();

    @PostMapping(path = "/{key}/{carName}", produces= MediaType.APPLICATION_JSON_VALUE)
    public String put(@PathVariable String key, @PathVariable String carName) {
        log.info("put key: {}, carName: {}", key, carName);
        return simpleMap.put(key, carName);
    }

    @GetMapping(value = "/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable String key) {
        log.info("get key: {}, carName: {}", key, simpleMap.get(key));
        return simpleMap.get(key);
    }
}

package io.reflectoring.cache.rest;


import io.reflectoring.cache.CacheClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cars")
public class Controller {

    private final CacheClient cacheClient;

    public Controller(CacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    @PostMapping(path = "/{number}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Car put(@RequestBody Car car, @PathVariable String number) {
        return cacheClient.put(number, car);
    }

    @GetMapping(value = "/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car get(@PathVariable String number) {
        return cacheClient.get(number);
    }
}

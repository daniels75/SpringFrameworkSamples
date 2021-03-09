package org.daniels.spring.resttemplate.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/exception")
@AllArgsConstructor
@Slf4j
public class RestWithExceptionHandling {
}

package com.example.interview;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class Account {

    @Value("${currency.denmark}")
    String currency;

    public String getCurrency() {
        return currency;
    }
}

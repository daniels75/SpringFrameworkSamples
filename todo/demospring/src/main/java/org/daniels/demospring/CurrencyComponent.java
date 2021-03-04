package org.daniels.demospring;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CurrencyComponent {

    @Value("${simple.currency}")
    private String currency;

    public String info() {
        return "Currency Component";
    }

    public String currency() {
        return currency;
    }
}

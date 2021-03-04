package org.daniels.spring.resttemplate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Car {
    private int age;
    private String name;

    public static Car create(int age, String name) {
        return new Car(age, name);
    }
}

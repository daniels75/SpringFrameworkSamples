package org.daniels.spring.resttemplate.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Car {
    private int age;
    private String name;

    public Car() {

    }
    public Car(int age, String name) {
        this.age = age;
        this.name = name;
    }
    public static Car create(int age, String name) {
        return new Car(age, name);
    }
}

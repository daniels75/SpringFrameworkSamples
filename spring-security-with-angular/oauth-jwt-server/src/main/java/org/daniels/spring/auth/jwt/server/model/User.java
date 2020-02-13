package org.daniels.spring.auth.jwt.server.model;

public class User {
    private Long id;
    private String name;
    private String surname;

    public User id(Long id) {
        this.id = id;
        return this;
    }

    public User name(String name) {
        this.name  = name;
        return this;
    }

    public User surname(String surname){
        this.surname = surname;
        return this;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

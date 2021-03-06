package org.daniels.spring.demo.factory.example;

public class FakeDatasource {
    private String username;
    private String password;
    private String url;
    private String urlFromEnv;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlFromEnv() {
        return urlFromEnv;
    }

    public void setUrlFromEnv(String urlFromEnv) {
        this.urlFromEnv = urlFromEnv;
    }
}

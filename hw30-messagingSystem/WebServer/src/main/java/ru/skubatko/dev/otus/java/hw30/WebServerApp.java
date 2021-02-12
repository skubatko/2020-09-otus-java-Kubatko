package ru.skubatko.dev.otus.java.hw30;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "ru.skubatko.dev.otus.java.hw30.config")
public class WebServerApp {

    public static void main(String[] args) {
        SpringApplication.run(WebServerApp.class, args);
    }
}

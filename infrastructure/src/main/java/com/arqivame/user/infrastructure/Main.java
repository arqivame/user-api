package com.arqivame.user.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arqivame.user.infrastructure.configuration.WebServerConfig;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(WebServerConfig.class, args);
    }

}

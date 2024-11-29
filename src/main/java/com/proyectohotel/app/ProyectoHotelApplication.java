package com.proyectohotel.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.proyectohotel.app.repository")
public class ProyectoHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoHotelApplication.class, args);
    }
}

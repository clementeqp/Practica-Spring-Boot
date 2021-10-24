package com.clemen.Ejercicio4.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/saludo")
    public String saludarAmigos(){
        return "Hola chicos, os saludo desde un controller en Spring Boot";
    }
}

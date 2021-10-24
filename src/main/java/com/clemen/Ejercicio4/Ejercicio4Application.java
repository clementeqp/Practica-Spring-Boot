package com.clemen.Ejercicio4;

import com.clemen.Ejercicio4.entities.Labtop;
import com.clemen.Ejercicio4.repository.LabtopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Ejercicio4Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Ejercicio4Application.class, args);

		LabtopRepository repository = context.getBean(LabtopRepository.class);

		Labtop labtop1 = new Labtop(null, "Lenovo", "Thinkpad", 16,599.99, LocalDate.of(2017,12,15),true);
		Labtop labtop2 = new Labtop(null, "HP", "XPro", 16,699.99, LocalDate.of(2018, 1, 15),true);
		Labtop labtop3 = new Labtop(null, "SlimBook", "Pro", 16,999.99, LocalDate.of(2021,1,15),false);

		repository.save(labtop1);
		repository.save(labtop2);
		repository.save(labtop3);

		// Labtops almacenados
		System.out.println("Numero de Labtops : " + repository.findAll().size());

	}

}

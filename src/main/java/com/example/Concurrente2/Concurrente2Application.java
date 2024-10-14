package com.example.Concurrente2;

import com.example.Concurrente2.Service.DatosService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@SpringBootApplication
public class Concurrente2Application  {





	public static void main(String[] args) {
		SpringApplication.run(Concurrente2Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(DatosService datosService) {
		return args -> {
			datosService.guardarDatos();
		};

	}




}

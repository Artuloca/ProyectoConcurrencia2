package com.example.Concurrente2;

import com.example.Concurrente2.Service.DatosService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Concurrente2Application  {

	public static void main(String[] args) {
		SpringApplication.run(Concurrente2Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(DatosService datosService) {
		return args -> {
			datosService.guardarDatos();
			datosService.ProcesarDatos();
			int lineaProcesadas = datosService.getLineaProcesadas();
			System.out.println("Se ha terminado el procesamiento: " + lineaProcesadas + " lineas procesadas");
		};

	}



}

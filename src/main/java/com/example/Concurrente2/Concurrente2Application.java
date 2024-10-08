package com.example.Concurrente2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@SpringBootApplication
public class Concurrente2Application implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Concurrente2Application.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		// Crea la tabla en MySQL

		String sql = "DROP TABLE IF EXISTS usuarios";
		jdbcTemplate.execute(sql);


		 sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
				"age INT, " +
				"workclass VARCHAR(100), " +
				"fnlwgt INT, " +
				"education VARCHAR(100), " +
				"education_num INT, " +
				"marital_status VARCHAR(100), " +
				"occupation VARCHAR(100), " +
				"relationship VARCHAR(100), " +
				"race VARCHAR(100), " +
				"sex VARCHAR(100), " +
				"capital_gain INT, " +
				"capital_loss INT, " +
				"hours_per_week INT, " +
				"native_country VARCHAR(100), " +
				"income VARCHAR(100))";
		jdbcTemplate.execute(sql);

		// Lee el archivo CSV y lo importa a la tabla
		String filePath = "C:\\Users\\danie\\Desktop\\datos\\income.csv";
		File file = new File(filePath);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			br.readLine(); // Salta la primera línea (cabeceras)
			int contador = 0;
			while ((line = br.readLine()) != null) {
				String[] datos = line.split(",");
				System.out.println("Importando línea " + ++contador + ": " + line);
				sql = "INSERT INTO usuarios (age, workclass, fnlwgt, education, education_num, marital_status, occupation, relationship, race, sex, capital_gain, capital_loss, hours_per_week, native_country, income) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql, datos);
			}
		} catch (IOException e) {
			System.out.println("Error leyendo el archivo CSV: " + e.getMessage());
		}
	}
}

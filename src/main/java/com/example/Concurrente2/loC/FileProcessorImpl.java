package com.example.Concurrente2.loC;

import com.example.Concurrente2.Entity.Datos;
import com.example.Concurrente2.Repository.DatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

@Service
public class FileProcessorImpl implements FileProcessor {

    @Autowired
    private DatosRepository datosRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Semaphore semaphore = new Semaphore(4);

    @Override
    public void procesar(File file) {
        jdbcTemplate.execute("ALTER TABLE datos MODIFY COLUMN id BIGINT AUTO_INCREMENT");

        Integer rowCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM datos",
                Integer.class
        );

        if (rowCount != null && rowCount > 0) {
            System.out.println("La tabla 'datos' ya contiene datos. No se ejecutará la lógica de importación.");
            return;
        } else {
            System.out.println("La tabla 'datos' no contiene datos. Se ejecutará la lógica de importación.");
        }

        datosRepository.deleteAllInBatch();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();

            String linea;
            List<Datos> batch = new ArrayList<>();
            LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

            while ((linea = br.readLine()) != null) {
                queue.put(linea);
            }

            for (int i = 0; i < 4; i++) {
                new Thread(() -> {
                    try {
                        semaphore.acquire();
                        String line;
                        while ((line = queue.poll()) != null) {
                            String[] campos = line.split(",");
                            if (campos.length != 15) {
                                System.err.println("Línea con formato incorrecto: " + line);
                                continue;
                            }

                        Datos datos = new Datos();
                        try {
                            datos.setAge(Integer.parseInt(campos[0]));
                            datos.setWorkclass(campos[1]);
                            datos.setFnlwgt(Integer.parseInt(campos[2]));
                            datos.setEducation(campos[3]);
                            datos.setEducation_num(Integer.parseInt(campos[4]));
                            datos.setMarital_status(campos[5]);
                            datos.setOccupation(campos[6]);
                            datos.setRelationship(campos[7]);
                            datos.setRace(campos[8]);
                            datos.setSex(campos[9]);
                            datos.setCapital_gain(Integer.parseInt(campos[10]));
                            datos.setCapital_loss(Integer.parseInt(campos[11]));
                            datos.setHours_per_week(Integer.parseInt(campos[12]));
                            datos.setNative_country(campos[13]);
                            datos.setIncome(campos[14]);
                        } catch (NumberFormatException e) {
                            System.err.println("Error al parsear la línea: " + line);
                            continue;
                        }

                        synchronized (batch) {
                            batch.add(datos);
                            if (batch.size() == 50) {
                                datosRepository.saveAll(batch);
                                batch.forEach(d -> System.out.println("Importando línea con id: " + d.getId()));
                                batch.clear();
                            }
                        }
                    }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        semaphore.release();
                    }
                }).start();
            }

            while (semaphore.availablePermits() < 4) {
                Thread.sleep(1000);
            }
            if (!batch.isEmpty()) {
                datosRepository.saveAll(batch);
                batch.forEach(d -> System.out.println("Importando línea con id: " + d.getId()));
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Fallo al leer el archivo", e);
        }
    }
}
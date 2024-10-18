package com.example.Concurrente2.Service;

import com.example.Concurrente2.Config.ExecutorConfig;
import com.example.Concurrente2.Entity.Datos;
import com.example.Concurrente2.Repository.DatosRepository;
import com.example.Concurrente2.loC.DatosProcessor;
import com.example.Concurrente2.loC.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DatosService {

    @Autowired
    private DatosRepository datosRepository;


    @Autowired
    private DatosProcessor datosProcessor;

    @Autowired
    private FileProcessor fileProcessor;

    AtomicInteger lineaDBProcesadas = new AtomicInteger();


    private final Semaphore semaphore = new Semaphore(4);


    public void procesarArchivos() {
        System.out.println("Procesando income.csv");
        File file = new File("src/main/resources/income.csv");
        if (!file.exists() || !file.isFile()) {
            System.out.println("No hay archivo para procesar");
        } else {
                    fileProcessor.procesar(file);
        }
    }



    public void ProcesarDatos() {
        System.out.println("Procesando datos de la base de datos");
        if (datosRepository.count() == 0) {
            System.out.println("No hay datos para procesar");
        } else {
            List<Datos> datos = datosRepository.findAll();
            ExecutorService executor = ExecutorConfig.taskExecutor();
            for (Datos dato : datos) {
                executor.execute(() -> {
                    try {
                        semaphore.acquire();
                        synchronized (this) {
                            datosProcessor.procesar(dato);
                            lineaDBProcesadas.getAndIncrement();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        semaphore.release();
                    }
                });
            }

            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                        System.err.println("Executor did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getLineaDBProcesadas() {
        return lineaDBProcesadas.get();
    }

}

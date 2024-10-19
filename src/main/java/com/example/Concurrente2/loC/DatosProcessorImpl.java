package com.example.Concurrente2.loC;

import com.example.Concurrente2.Entity.Datos;
import org.springframework.stereotype.Service;

@Service
public class DatosProcessorImpl implements DatosProcessor {

    @Override
    public void procesar(Datos datos) {
        System.out.println("Procesada línea con ID: " + datos.getId());
    }
}
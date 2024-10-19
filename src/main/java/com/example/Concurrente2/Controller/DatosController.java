package com.example.Concurrente2.Controller;


import com.example.Concurrente2.Entity.Datos;
import com.example.Concurrente2.Repository.DatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/datos")
public class DatosController {

    @Autowired
    private DatosRepository datosRepository;

    @GetMapping
    public List<Datos> getDatos() {
        List<Datos> datos = datosRepository.findAll();

        // Verificación con log de los datos que se están recuperando
        datos.forEach(d -> System.out.println(d.toString()));

        return datos;
    }
}



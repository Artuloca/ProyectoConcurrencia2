package com.example.Concurrente2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UsuarioService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void analizarDatos() {
        String sql = "SELECT * FROM usuarios";
        List<Map<String, Object>> usuarios = jdbcTemplate.queryForList(sql);

        // Crear un pool de hilos
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Implementa la lógica de análisis concurrente aquí
        for (Map<String, Object> usuario : usuarios) {
            executorService.submit(() -> {
                // Contar cuántos usuarios tienen ingresos mayores a 50K
                if (">50K".equals(usuario.get("income"))) {
                    System.out.println("Usuario con ingresos mayores a 50K: " + usuario);
                }
            });
        }

        // Apagar el executor service
        executorService.shutdown();
    }
}

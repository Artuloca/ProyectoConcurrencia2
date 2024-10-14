package com.example.Concurrente2.Service;

import com.example.Concurrente2.Entity.Datos;
import com.example.Concurrente2.Repository.DatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class DatosService {


    @Autowired
    private DatosRepository datosRepository;


    public void guardarDatos() {

        datosRepository.deleteAllInBatch();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/income.csv"))) {
            br.readLine();

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                Datos datos = new Datos();
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

                datosRepository.save(datos);
            }
        } catch (IOException e) {
            throw new RuntimeException("fallo al obtener los datos", e);
        }

    }


}

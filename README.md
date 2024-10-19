# Proyecto Concurrente 2 hecho por Daniel Andrés Moreno Rey, Arturo Lopez Castaño y Rodrigo Rojas Redondo
https://github.com/Artuloca/ProyectoConcurrencia2.git

Este proyecto es una aplicación para el monitoreo de datos biologicos utilizando un frontend en Angular y D3.js para la visualización de gráficos y un backend en Spring Boot que gestiona la concurrencia a través de múltiples hilos.

## Tecnologías utilizadas

### Frontend:
- **Angular 18.2**
- **D3.js** para la creación de gráficos dinámicos.

### Backend:
- **Spring Boot** para gestionar la lógica del servidor.
- **JPA/Hibernate** para la interacción con la base de datos.
- **MySQL** como base de datos.
- **Docker** para la configuración de servicios, aunque no se utiliza para unificar el frontend y backend.

### Base de datos:
- **MySQL** configurado con Docker.

## Configuración del entorno

### Requisitos previos
- **Java 17** o superior.
- **Node.js** y **npm** (para ejecutar el frontend en Angular).
- **MySQL** instalado localmente o utilizando un contenedor Docker.

### Instalación del backend

1. Clona este repositorio en tu máquina local.
2. Asegúrate de tener **MySQL** corriendo en el puerto `3308` con una base de datos llamada `concurrente2`.
3. Inicia el backend desde la raíz del proyecto con:
   ./mvnw spring-boot:run
El backend estará disponible en http://localhost:8080.
Instalación del frontend
Navega al directorio /frontend:

cd frontend
Instala las dependencias:
npm install
Inicia el servidor de desarrollo de Angular:

ng serve


Funcionalidades principales
Visualización de gráficos interactivos que muestran diferentes relaciones de los datos.
Los datos incluyen variables como edad, horas trabajadas, relación familiar, entre otros.
El backend está preparado para gestionar múltiples hilos, lo que permite una alta concurrencia.
Estructura del proyecto
/src/main/java: Contiene el código fuente del backend en Spring Boot.
/frontend/src: Contiene el código fuente del frontend en Angular.
/docker: Configuración del contenedor Docker para la base de datos MySQL.

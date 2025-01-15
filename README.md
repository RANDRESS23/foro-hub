# CHALLENGE FOROHUB

## Descripción

*Foro Hub* es una aplicación backend diseñada para gestionar un foro interactivo donde los usuarios pueden crear, visualizar, y moderar topicos y respuestas segun su curso. El proyecto incluye permisos avanzados, roles predefinidos, y un sistema de inicialización automática de datos.

## Características

- Gestión de usuarios y topicos.
- CRUD completo de topicos.
- API REST construida con **Spring Boot**.
- Seguridad y autenticación.
- Acceso restringido a ciertas funcionalidades según el perfil del usuario.
- Migración de base de datos".

## Instalación

### Requisitos Previos

- Java 17 o superior instalado.
- Maven instalado.
- PostgreSQL configurado y en ejecución.

1. Crea una base de datos y comunicate con ella por las variables de entorno o modifica el properties.
   
2. Clona este repositorio:
  ```
  git clone https://github.com/RANDRESS23/foro-hub.git
  cd foro-hub
  ```

3. Configura las variables de entorno o el archivo application.properties:
  ```
  spring.datasource.url=${DATABASE_URL}
  spring.datasource.username=${DATABASE_USERNAME}
  spring.datasource.password=${DATABASE_PASSWORD}
  secret.key=${SECRET_KEY}
  ```

4. Ejecuta el proyecto:
  ```
  ./mvnw spring-boot:run
  ```

5. Accede a la aplicación en ```http://localhost:8080```.

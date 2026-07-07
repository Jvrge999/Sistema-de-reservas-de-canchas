# Sistema de Reservas de Canchas - Backend

Proyecto final desarrollado con arquitectura de microservicios para la gestión de complejos deportivos.

**Integrantes del Equipo:**
- Jorge Aguilera
- [Nombre de tu compañero/a]

**Link Video Demo Postman (Evaluación Parcial 2):** [https://youtu.be/4-V0iJpFha8](https://youtu.be/4-V0iJpFha8)

## Novedades de la Fase Actual (Evaluación 3)
En esta etapa, el ecosistema se ha robustecido implementando Service Discovery, Enrutamiento Centralizado, Pruebas Unitarias y Documentación interactiva de APIs, garantizando alta disponibilidad y calidad de código.

## Stack Tecnológico y Prácticas Aplicadas
- **Backend:** Java 21 & Spring Boot 3.2.5
- **Arquitectura Cloud:** Spring Cloud (API Gateway, Netflix Eureka Server).
- **Comunicación:** Spring Cloud OpenFeign & API Gateway.
- **Persistencia:** Spring Data JPA, Bases de Datos MySQL independientes (Docker) / H2 (Entorno de pruebas).
- **Testing:** JUnit 5, Mockito, Spring Boot Test (Cobertura de Controllers, Services, Repositories y Models).
- **Documentación:** OpenAPI 3.0 / Swagger UI.

## Puertos y Servicios
| Microservicio | Puerto | Función Principal |
| :--- | :--- | :--- |
| **eureka-server** | 8761 | Servidor de descubrimiento de servicios. |
| **api-gateway** | 8080 | Enrutamiento centralizado y balanceo. |
| **ms-canchas** | 8081 | Gestión de canchas y tipos. |
| **ms-equipamiento**| 8082 | Arriendo de implementos. |
| **ms-usuarios** | 8083 | Gestión de perfiles. |
| **ms-reservas** | 8084 | Orquestación de agendamientos. |
| **ms-pagos** | 8085 | Procesamiento de pagos (Feign). |
| **ms-autenticacion**| 8086 | Seguridad y accesos. |
| **ms-notificaciones**| 8087 | Alertas al cliente. |
| **ms-fairplay** | 8088 | Conducta y sanciones. |
| **ms-resenas** | 8089 | Feedback post-partido. |
| **ms-reportes** | 8090 | Análisis estadístico. |

## Rutas Principales (API Gateway)
Todo el tráfico externo es centralizado a través del API Gateway en el puerto `8080`.
- **Usuarios:** `http://localhost:8080/usuarios`
- **Canchas:** `http://localhost:8080/canchas`
- **Reservas:** `http://localhost:8080/reservas`
- **Pagos:** `http://localhost:8080/pagos`
- **Equipamiento:** `http://localhost:8080/equipamientos`

## Documentación Swagger (OpenAPI)
Las especificaciones y pruebas de los microservicios principales están disponibles en las siguientes rutas (requiere tener el ecosistema levantado):
- **Usuarios:** `http://localhost:8083/doc/swagger-ui.html`
- **Canchas:** `http://localhost:8081/doc/swagger-ui.html`
- **Reservas:** `http://localhost:8084/doc/swagger-ui.html`
- **Pagos:** `http://localhost:8085/doc/swagger-ui.html`
- **Equipamiento:** `http://localhost:8082/doc/swagger-ui.html`

## Instalación y Despliegue
Para desplegar todo el ecosistema, asegúrese de tener Docker abierto y ejecute en la raíz del proyecto:
```bash
docker compose up -d --build

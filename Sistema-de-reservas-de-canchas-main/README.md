# Sistema de Reservas de Canchas - Backend
Proyecto final desarrollado con arquitectura de microservicios para la gestión de complejos deportivos.

## Stack Tecnológico
- **Java 21** & **Spring Boot 3.2.5**
- **Arquitectura:** 10 Microservicios independientes.
- **Comunicación:** Spring Cloud OpenFeign & API Gateway (Puerto 8080).
- **Persistencia:** Bases de Datos MySQL independientes (Docker).

## Puertos y Servicios
| Microservicio | Puerto | Función Principal |
| :--- | :--- | :--- |
| **api-gateway** | 8080 | Enrutamiento centralizado. |
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

## Instalación
```bash
docker compose up -d --build
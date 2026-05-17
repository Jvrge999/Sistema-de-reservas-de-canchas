# Proyecto Semestral: Sistema de Reservas de Canchas

## Integrante
- Jorge Aguilera

## Estado del Sistema (Hito 1.5)

| Microservicio  | Puerto | DB Name  | Funcionalidad         |
| :------------- | :----- | :------- | :-------------------- |
| ms-usuarios    | 8083   | db_usuarios | CRUD de gestión de clientes |
| ms-canchas     | 8081   | db_canchas  | CRUD de gestión de canchas |
| ms-equipamiento| 8082   | db_equipamiento | CRUD de implementos |
| ms-reservas    | 8084   | db_reservas | En desarrollo (Hito 2) |
| ms-pagos       | 8085   | db_pagos    | En desarrollo (Hito 2) |

## Despliegue Técnico
- **Instancia:** AWS EC2 t3.large (Ubuntu 24.04)
- **Comando de inicio:** `docker compose up -d`

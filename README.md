# Sistema de Gestión para Restaurantes

## Descripción

Este sistema ha sido desarrollado para optimizar y facilitar el control interno de restaurantes. La aplicación permite la gestión eficiente de órdenes, mesas y menú, implementando además un sistema de roles (Administrador, Chef y Mesero) y autenticación segura para cada usuario.

## Funcionalidades principales

- **Gestión de órdenes:** Creación, modificación y consulta de órdenes de clientes.
- **Gestión de menú:** Administración de platillos y bebidas disponibles.
- **Gestión de mesas:** Asignación, administración y control de disponibilidad de mesas.
- **Sistema de roles:** 
  - **Administrador:** Control total del sistema (usuarios, roles, menú, mesas y órdenes).
  - **Chef:** Visualización y actualización de órdenes en preparación.
  - **Mesero (Waiter):** Registro y seguimiento de órdenes, administración de mesas asignadas.
- **Autenticación:** Inicio de sesión seguro, acceso diferenciado según el rol.

## Diagrama Entidad-Relacion

![Diagrama Entidad-Relación de la base de datos](./Diagrama%20E-R.drawio.png)

## Tecnologías utilizadas

- **Spring Boot**
- **Java 17**
- **PostgreSQL**
- **Spring Data JPA**
- **Spring Security**
- **Spring Web**
- **Spring Validation**
- **Lombok**

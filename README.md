# Pizzalicious - Sistema de Gestión de Pizzería

Pizzalicious es un sistema de gestión de pedidos y ventas diseñado para una pizzería. Esta aplicación utiliza Spring Boot como marco de desarrollo y PostgreSQL como base de datos para ofrecer una experiencia eficiente y fácil de usar tanto para los clientes como para el personal de la pizzería.

## Características Destacadas

- **Autenticación de Usuarios**: La aplicación ofrece autenticación de usuarios para que los clientes puedan acceder a sus cuentas y realizar pedidos personalizados.

- **Gestión de Clientes**: Puedes buscar clientes por número de teléfono y obtener un historial de todas las órdenes realizadas por cada cliente.

- **Gestión de Pedidos**: Realiza un seguimiento eficiente de todas las órdenes, incluyendo las entregas y recogidas. También puedes obtener resúmenes de órdenes que incluyen detalles clave como el nombre del cliente y el total de la orden.

- **Gestión de Menú**: Administra fácilmente el menú de la pizzería, incluyendo la creación, modificación y eliminación de productos y categorías de pizzas.

- **Pedidos Aleatorios**: Ofrece la opción de seleccionar aleatoriamente una pizza disponible con un descuento aplicado, ideal para promociones especiales y ofertas sorpresa a los clientes.

## Requisitos de Desarrollo

Para ejecutar y desarrollar la aplicación Pizzalicious, necesitarás tener instalado lo siguiente:

- [Java 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/download/)
- [IDE de Desarrollo](https://www.jetbrains.com/idea/download/)

## Configuración del Entorno

1. Clona este repositorio:

   ```shell
   git clone git clone https://github.com/marvin211/pizzalicious.git
   ```
   
2. Abre el proyecto en tu IDE de desarrollo preferido.
3. Crea una base de datos PostgreSQL llamada `pizzalicious`.
4. Ejecuta el script `pizzalicious.sql` en la base de datos para los datos de prueba y el procedimiento almacenado para seleccionar aleatoriamente una pizza disponible aplicando un descuento.
5. Actualiza el archivo `application.properties` con tu configuración de base de datos.
6. Ejecuta la aplicación y navega a `http://localhost:8080` para ver la aplicación en funcionamiento.
7. Inicia sesión con el usuario `admin` y la contraseña `admin` para acceder a la aplicación.
8. ¡Disfruta de la aplicación!

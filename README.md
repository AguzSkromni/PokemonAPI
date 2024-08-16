Scope del Proyecto: API y Web Service para Gestión de Pokémon
Descripción General:
El proyecto consistirá en desarrollar una aplicación web sencilla utilizando Java Spring para gestionar una pequeña base de datos relacionada con Pokémon. El sistema permitirá realizar operaciones CRUD (Create, Read, Update, Delete) a través de peticiones HTTP y ofrecerá tanto un servicio REST como un servicio SOAP para interactuar con los datos. La interfaz gráfica será sencilla, permitiendo a los usuarios realizar estas operaciones desde un navegador web.

Objetivos del Proyecto:

Desarrollar una API REST y un servicio SOAP para gestionar datos de Pokémon.
Implementar operaciones CRUD (Post, Get, Put, Delete) sobre la base de datos relacional.
Diseñar una interfaz gráfica web simple para interactuar con la API.
Conectar la aplicación con una base de datos relacional que contenga dos tablas como máximo.
Utilizar buenas prácticas de programación y diseño de software, como el uso de patrones de diseño y pruebas unitarias.

Estructura del Proyecto
Modelo de Datos:

Tabla Pokémon: Contendrá información básica de cada Pokémon, como nombre, tipo, nivel, etc.
Tabla Entrenador: Relacionada con la tabla Pokémon, esta tabla contendrá datos de los entrenadores y los Pokémon que tienen en su equipo.
Relación entre tablas:

Un entrenador puede tener múltiples Pokémon (Relación uno a muchos).
Un Pokémon solo pertenece a un entrenador.
API REST:

GET /api/pokemon - Obtener la lista de Pokémon.

GET /api/pokemon/{id} - Obtener un Pokémon específico por su ID.

POST /api/pokemon - Crear un nuevo Pokémon.

PUT /api/pokemon/{id} - Actualizar los datos de un Pokémon existente.

DELETE /api/pokemon/{id} - Eliminar un Pokémon.

GET /api/entrenadores - Obtener la lista de entrenadores.

POST /api/entrenador - Crear un nuevo entrenador.

PUT /api/entrenador/{id} - Actualizar un entrenador existente.

DELETE /api/entrenador/{id} - Eliminar un entrenador.

Servicio SOAP:

Método getPokemonDetails(id) - Obtener detalles de un Pokémon por su ID.
Método getAllPokemon() - Obtener todos los Pokémon.
Método addPokemon(Pokemon) - Agregar un nuevo Pokémon.
Método updatePokemon(Pokemon) - Actualizar un Pokémon existente.
Método deletePokemon(id) - Eliminar un Pokémon por su ID.
Interfaz Gráfica:

Formulario para agregar/editar Pokémon.
Listado de Pokémon y entrenadores con opciones para editar y eliminar.
Formulario para agregar/editar entrenadores.
Interfaz sencilla para consumir el servicio SOAP.
Base de Datos Relacional:

Tecnología: MySQL o PostgreSQL.
Tablas:
pokemon (id, nombre, tipo, nivel, entrenador_id).
entrenador (id, nombre, edad, ciudad).
Tecnologías y Herramientas:

Backend: Java Spring Boot (con Spring MVC, Spring Data JPA).
Frontend: HTML, CSS, JavaScript (con una librería ligera como Bootstrap para estilizar).
Base de Datos: MySQL/PostgreSQL.
Servicios Web: Spring Web Services para SOAP y REST API.
Testing: JUnit para pruebas unitarias.

# Servicio REST de Usuarios con Spring Boot

Este proyecto es un ejemplo educativo de cómo desarrollar un servicio REST utilizando Spring Boot, JPA y MySQL. El proyecto permite gestionar usuarios en una base de datos MySQL.

## 📋 Características

- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Para el acceso a datos
- **MySQL 8.0** - Base de datos relacional
- **Docker** - Para contenedorizar la base de datos
- **Validaciones** - Validación de datos de entrada
- **Auditoría** - Campos de auditoría automáticos (fecha creación, usuario, IP, etc.)

## 🏗️ Estructura del Proyecto

```
PVB25/
├── src/
│   ├── main/
│   │   ├── java/com/teclemas/usuario/
│   │   │   ├── UsuarioServiceApplication.java    # Clase principal
│   │   │   ├── model/
│   │   │   │   └── Usuario.java                  # Entidad JPA
│   │   │   ├── repository/
│   │   │   │   └── UsuarioRepository.java        # Repositorio JPA
│   │   │   ├── service/
│   │   │   │   └── UsuarioService.java           # Lógica de negocio
│   │   │   ├── controller/
│   │   │   │   └── UsuarioController.java         # Controlador REST
│   │   │   ├── dto/
│   │   │   │   └── UsuarioDTO.java                # Objeto de transferencia
│   │   │   └── exception/
│   │   │       └── GlobalExceptionHandler.java    # Manejo de excepciones
│   │   └── resources/
│   │       └── application.properties             # Configuración
├── docker-entrypoint-initdb.d/
│   └── 01-init.sql                                # Script de inicialización
├── Dockerfile                                      # Dockerfile para MySQL
├── docker-compose.yml                              # Orquestación de contenedores
├── pom.xml                                         # Dependencias Maven
└── README.md                                       # Este archivo
```

## 🚀 Requisitos Previos

- **Java 17** o superior
- **Maven 3.6+**
- **Docker** y **Docker Compose** (para la base de datos)

## 📦 Instalación y Configuración

### 1. Clonar o descargar el proyecto

```bash
cd /home/wgaibor/Documentos/joao/TecLemas/PV/PVB25/usuario
```

### 2. Levantar la base de datos MySQL con Docker

```bash
docker-compose up -d
```

Esto creará un contenedor MySQL con:
- **Base de datos**: `usuarios_db`
- **Usuario root**: `root` / Contraseña: `root123`
- **Puerto**: `3306`

### 3. Verificar que MySQL esté corriendo

```bash
docker ps
```

Deberías ver el contenedor `mysql-usuarios` en ejecución.

### 4. Compilar el proyecto

```bash
mvn clean install
```

### 5. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

O si prefieres usar el JAR:

```bash
java -jar target/usuario-service-1.0.0.jar
```

La aplicación estará disponible en: `http://localhost:8080`

## 📊 Estructura de la Tabla Usuarios

La tabla `usuarios` se crea automáticamente mediante JPA y contiene los siguientes campos:

### Campos Principales
- `id_usuario` (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- `identificacion` (VARCHAR(20), UNIQUE, NOT NULL)
- `nombre` (VARCHAR(100), NOT NULL)
- `apellido` (VARCHAR(100), NOT NULL)
- `fe_nacimiento` (DATE, NOT NULL)
- `celular` (VARCHAR(10))
- `mail` (VARCHAR(150), UNIQUE, NOT NULL)
- `direccion` (VARCHAR(200))
- `ciudad` (VARCHAR(50))
- `pais` (VARCHAR(50))
- `activo` (BOOLEAN, DEFAULT TRUE)

### Campos de Auditoría
- `fe_creacion` (DATETIME, NOT NULL) - Se establece automáticamente al crear
- `usr_creacion` (VARCHAR(50), NOT NULL) - Usuario que creó el registro
- `ip_creacion` (VARCHAR(45)) - IP desde donde se creó
- `fe_modificacion` (DATETIME) - Se actualiza automáticamente al modificar
- `usr_modificacion` (VARCHAR(50)) - Usuario que modificó el registro
- `ip_modificacion` (VARCHAR(45)) - IP desde donde se modificó

## 🔌 Endpoints REST Disponibles

### GET - Obtener todos los usuarios
```http
GET http://localhost:8080/api/usuarios
```

### GET - Obtener usuarios activos
```http
GET http://localhost:8080/api/usuarios/activos
```

### GET - Obtener usuario por ID
```http
GET http://localhost:8080/api/usuarios/{id}
```

**Ejemplo:**
```http
GET http://localhost:8080/api/usuarios/1
```

### GET - Obtener usuario por identificación
```http
GET http://localhost:8080/api/usuarios/identificacion/{identificacion}
```

**Ejemplo:**
```http
GET http://localhost:8080/api/usuarios/identificacion/1234567890
```

### GET - Obtener usuario por correo electrónico
```http
GET http://localhost:8080/api/usuarios/mail/{mail}
```

**Ejemplo:**
```http
GET http://localhost:8080/api/usuarios/mail/juan.perez@example.com
```

### GET - Buscar usuarios por nombre o apellido
```http
GET http://localhost:8080/api/usuarios/buscar?q={busqueda}
```

**Ejemplo:**
```http
GET http://localhost:8080/api/usuarios/buscar?q=Juan
```

### POST - Crear un nuevo usuario
```http
POST http://localhost:8080/api/usuarios
Content-Type: application/json
```

**Cuerpo de la petición:**
```json
{
  "identificacion": "1234567890",
  "nombre": "Juan",
  "apellido": "Pérez",
  "feNacimiento": "1990-05-15",
  "celular": "0987654321",
  "mail": "juan.perez@example.com",
  "direccion": "Calle Principal 123",
  "ciudad": "Quito",
  "pais": "Ecuador",
  "activo": true
}
```

**Headers opcionales para auditoría:**
```
X-Usuario: admin
```

### PUT - Actualizar un usuario existente
```http
PUT http://localhost:8080/api/usuarios/{id}
Content-Type: application/json
```

**Ejemplo:**
```http
PUT http://localhost:8080/api/usuarios/1
Content-Type: application/json

{
  "identificacion": "1234567890",
  "nombre": "Juan Carlos",
  "apellido": "Pérez González",
  "feNacimiento": "1990-05-15",
  "celular": "0987654321",
  "mail": "juan.perez@example.com",
  "direccion": "Calle Principal 456",
  "ciudad": "Guayaquil",
  "pais": "Ecuador",
  "activo": true
}
```

### DELETE - Eliminar un usuario (eliminación lógica)
```http
DELETE http://localhost:8080/api/usuarios/{id}
```

**Ejemplo:**
```http
DELETE http://localhost:8080/api/usuarios/1
```

## 🧪 Ejemplos de Uso con cURL

### Crear un usuario
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -H "X-Usuario: admin" \
  -d '{
    "identificacion": "1234567890",
    "nombre": "Juan",
    "apellido": "Pérez",
    "feNacimiento": "1990-05-15",
    "celular": "0987654321",
    "mail": "juan.perez@example.com",
    "direccion": "Calle Principal 123",
    "ciudad": "Quito",
    "pais": "Ecuador"
  }'
```

### Obtener todos los usuarios
```bash
curl http://localhost:8080/api/usuarios
```

### Obtener un usuario por ID
```bash
curl http://localhost:8080/api/usuarios/1
```

### Buscar usuarios
```bash
curl "http://localhost:8080/api/usuarios/buscar?q=Juan"
```

### Actualizar un usuario
```bash
curl -X PUT http://localhost:8080/api/usuarios/1 \
  -H "Content-Type: application/json" \
  -H "X-Usuario: admin" \
  -d '{
    "identificacion": "1234567890",
    "nombre": "Juan Carlos",
    "apellido": "Pérez",
    "feNacimiento": "1990-05-15",
    "celular": "0987654321",
    "mail": "juan.perez@example.com",
    "ciudad": "Guayaquil"
  }'
```

### Eliminar un usuario
```bash
curl -X DELETE http://localhost:8080/api/usuarios/1 \
  -H "X-Usuario: admin"
```

## 🔍 Validaciones Implementadas

- **Identificación**: Obligatoria, entre 5 y 20 caracteres, única
- **Nombre**: Obligatorio, entre 2 y 100 caracteres
- **Apellido**: Obligatorio, entre 2 y 100 caracteres
- **Fecha de nacimiento**: Obligatoria, debe ser una fecha pasada
- **Celular**: 10 dígitos numéricos
- **Correo electrónico**: Obligatorio, formato válido, único
- **Dirección, Ciudad, País**: Opcionales, con límites de caracteres

## 🛠️ Tecnologías y Dependencias

- **Spring Boot Starter Web** - Para crear servicios REST
- **Spring Boot Starter Data JPA** - Para acceso a datos con JPA
- **MySQL Connector** - Driver para MySQL
- **Spring Boot Starter Validation** - Para validaciones
- **Lombok** (opcional) - Para reducir código boilerplate

## 📝 Notas para Estudiantes

### Conceptos Clave Aprendidos

1. **Arquitectura en Capas**:
   - **Controller**: Maneja las peticiones HTTP
   - **Service**: Contiene la lógica de negocio
   - **Repository**: Acceso a datos mediante JPA
   - **Model/Entity**: Representa la tabla en la base de datos
   - **DTO**: Objeto de transferencia de datos

2. **JPA (Java Persistence API)**:
   - Anotaciones: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`
   - Ciclo de vida: `@PrePersist`, `@PreUpdate`
   - Repositorios: Extienden `JpaRepository`

3. **REST API**:
   - Métodos HTTP: GET, POST, PUT, DELETE
   - Códigos de estado: 200, 201, 400, 404, 500
   - Validaciones con `@Valid` y `@RequestBody`

4. **Auditoría**:
   - Campos automáticos para rastrear quién y cuándo se creó/modificó un registro
   - Uso de `@PrePersist` y `@PreUpdate`

5. **Docker**:
   - Contenedorización de la base de datos
   - Uso de `docker-compose` para orquestación

## 🐛 Solución de Problemas

### Error de conexión a MySQL
- Verifica que el contenedor esté corriendo: `docker ps`
- Verifica los logs: `docker logs mysql-usuarios`
- Asegúrate de que el puerto 3306 no esté en uso

### Error de compilación
- Verifica que tengas Java 17 instalado: `java -version`
- Verifica que Maven esté instalado: `mvn -version`
- Limpia y recompila: `mvn clean install`

### La tabla no se crea
- Verifica la configuración en `application.properties`
- Asegúrate de que `spring.jpa.hibernate.ddl-auto=update`
- Revisa los logs de la aplicación

## 📚 Recursos Adicionales

- [Documentación de Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación de Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Documentación de MySQL](https://dev.mysql.com/doc/)
- [Documentación de Docker](https://docs.docker.com/)

## 👨‍🏫 Autor

Proyecto educativo para la enseñanza de desarrollo de software con Spring Boot.

---

**¡Feliz aprendizaje!** 🚀

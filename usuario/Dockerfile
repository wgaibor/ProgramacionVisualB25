# Dockerfile para MySQL
FROM mysql:8.0

# Variables de entorno para MySQL
ENV MYSQL_ROOT_PASSWORD=root123
ENV MYSQL_DATABASE=usuarios_db
ENV MYSQL_USER=usuario_app
ENV MYSQL_PASSWORD=usuario123

# Exponer el puerto de MySQL
EXPOSE 3306

# Comando para inicializar la base de datos
# Los scripts SQL en /docker-entrypoint-initdb.d/ se ejecutarán automáticamente
COPY ./docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/


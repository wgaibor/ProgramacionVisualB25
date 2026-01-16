# Dockerfile para MySQL - Base de datos de la cafetería
FROM mysql:5.7

# Variables de entorno para MySQL
ENV MYSQL_ROOT_PASSWORD=root123
ENV MYSQL_DATABASE=cafeteria_db
ENV MYSQL_USER=cafeteria_user
ENV MYSQL_PASSWORD=cafeteria_pass

# Copiar el script SQL de inicialización
COPY table_cafeteria.sql /docker-entrypoint-initdb.d/

# Exponer el puerto de MySQL
EXPOSE 3306

# El script se ejecutará automáticamente al iniciar el contenedor
# gracias a /docker-entrypoint-initdb.d/
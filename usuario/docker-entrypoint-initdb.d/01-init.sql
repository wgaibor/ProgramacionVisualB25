-- Script de inicialización de la base de datos
-- Este script se ejecuta automáticamente cuando se crea el contenedor MySQL

-- Crear la base de datos si no existe (aunque ya se crea con la variable de entorno)
CREATE DATABASE IF NOT EXISTS usuarios_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE usuarios_db;

-- La tabla se creará automáticamente mediante JPA/Hibernate con ddl-auto=update
-- Pero podemos crear un script de ejemplo aquí si lo deseamos


-- =====================================================
-- SCRIPT DE CREACIÓN DE BASE DE DATOS PARA CAFETERÍA
-- =====================================================

-- =====================================================
-- TABLA: admi_producto
-- Almacena los productos que se venden en la cafetería
-- =====================================================
CREATE TABLE IF NOT EXISTS admi_producto (
    id_producto             INT AUTO_INCREMENT PRIMARY KEY,
    item                    VARCHAR(255) NOT NULL COMMENT 'Nombre del producto',
    precio_unitario         DECIMAL(10, 2) NOT NULL COMMENT 'Precio unitario del producto',
    descripcion             VARCHAR(255) COMMENT 'Descripción del producto',
    categoria               VARCHAR(63) COMMENT 'Categoria del producto: bebida, comida, postre, etc',
    stock_disponible        INT DEFAULT 0 COMMENT 'Cantidad disponible en stock',
    estado                  VARCHAR(1) DEFAULT 'A' COMMENT 'Estado: A= Activo, I= Inactivo',
    usr_creacion            VARCHAR(63) NOT NULL COMMENT 'Usuario que creó el registro',
    fe_creacion             TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    usr_ult_modificacion    VARCHAR(63) COMMENT 'Usuario que modificó por última vez el registro',
    fe_ult_modificacion     TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación',
    INDEX idx_estado (estado),
    INDEX idx_categoria (categoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- TABLA: admi_usuario
-- Almacena los clientes del negocio
-- =====================================================
CREATE TABLE IF NOT EXISTS admi_usuario (
    id_usuario              INT AUTO_INCREMENT PRIMARY KEY,
    nombre                  VARCHAR(255) NOT NULL COMMENT 'Nombre completo del usuario',
    email                   VARCHAR(127) COMMENT 'Correo electrónico del usuario',
    telefono                VARCHAR(15)  COMMENT 'Teléfono de contacto',
    direccion               VARCHAR(255) COMMENT 'Dirección del usuario',
    tipo_usuario            VARCHAR(15) DEFAULT 'CLIENTE' COMMENT 'Tipo: CLIENTE, EMPLEADO, ADMIN',
    fecha_nacimiento        DATE COMMENT 'Fecha de nacimiento',
    estado                  VARCHAR(1) DEFAULT 'A' COMMENT 'Estado: A= Activo, I= Inactivo',
    usr_creacion            VARCHAR(63) NOT NULL COMMENT 'Usuario que creó el registro',
    fe_creacion             TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    usr_ult_modificacion    VARCHAR(63) COMMENT 'Usuario que modificó por última vez el registro',
    fe_ult_modificacion     TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación',
    INDEX idx_estado (estado),
    INDEX idx_tipo_usuario (tipo_usuario),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- TABLA: admi_insumo
-- Almacena los insumos necesarios para preparar productos
-- =====================================================
CREATE TABLE IF NOT EXISTS admi_insumo (
    id_insumo               INT AUTO_INCREMENT PRIMARY KEY,
    nombre                  VARCHAR(255) NOT NULL COMMENT 'Nombre del insumo',
    descripcion             VARCHAR(255) COMMENT 'Descripción del insumo',
    unidad_medida           VARCHAR(31) COMMENT 'Unidad de medida (kg, litros, unidades, etc)',
    cantidad_disponible     DECIMAL(10, 2) DEFAULT 0 COMMENT 'Cantidad disponible en inventario',
    precio_compra           DECIMAL(10, 2) COMMENT 'Precio de compra del insumo',
    categoria               VARCHAR(127) COMMENT 'Categoria (aceite, café, limpieza, etc)',
    proveedor               VARCHAR(255) COMMENT 'Nombre del proveedor',
    fecha_vencimiento       DATE COMMENT 'Fecha de vencimiento (si aplicaré)',
    estado                  VARCHAR(1) DEFAULT 'A' COMMENT 'Estado: A= Activo, I= Inactivo',
    usr_creacion            VARCHAR(63) NOT NULL COMMENT 'Usuario que creó el registro',
    fe_creacion             TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    usr_ult_modificacion    VARCHAR(63) COMMENT 'Usuario que modificó por última vez el registro',
    fe_ult_modificacion     TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación',
    INDEX idx_estado (estado),
    INDEX idx_categoria (categoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- TABLA: info_ventas
-- Registra las transacciones de ventas diarias
-- =====================================================
CREATE TABLE IF NOT EXISTS info_ventas (
    id_venta                INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id              INT COMMENT 'ID  del cliente que realizo la compra, referencia de la tabla admi_usuario.id_usuario',
    producto_id             INT NOT NULL COMMENT 'ID del producto vendedido, referencia de la tabla admi_producto.id_producto',
    cantidad                INT NOT NULL DEFAULT 1 COMMENT 'Cantidad de productos vendidos',
    precio_unitario         DECIMAL(10, 2) NOT NULL COMMENT 'Precio unitario al momento de la venta',
    subtotal                DECIMAL(10, 2) NOT NULL COMMENT 'Subtotal (cantidad * precio_unitario)',
    descuento               DECIMAL(10, 2) DEFAULT 0 COMMENT 'Descuento aplicado',
    total                   DECIMAL(10, 2) NOT NULL COMMENT 'Total a pagar (subtotal - descuento)',
    metodo_pago             VARCHAR(31) COMMENT 'Método de pago (efectivo, transferencia, tarjeta)',
    fecha_venta             TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de la venta',
    observacion             VARCHAR(255) COMMENT 'Observaciones adicionales',
    estado                  VARCHAR(1) DEFAULT 'A' COMMENT 'Estado: A= Activo, I= Inactivo',
    usr_creacion            VARCHAR(63) NOT NULL COMMENT 'Usuario que creó el registro',
    fe_creacion             TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    usr_ult_modificacion    VARCHAR(63) COMMENT 'Usuario que modificó por última vez el registro',
    fe_ult_modificacion     TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación',
    INDEX idx_fecha_venta (fecha_venta),
    INDEX idx_usuario_id (usuario_id),
    INDEX idx_producto_id (producto_id),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- TABLA: info_gastos
-- Registra los gastos que incurre el negocio
-- =====================================================
CREATE TABLE IF NOT EXISTS info_gastos (
    id_gasto                INT AUTO_INCREMENT PRIMARY KEY,
    concepto                VARCHAR(255) NOT NULL COMMENT 'Concepto del gasto',
    descripcion             VARCHAR(255) COMMENT 'Descripción detallada del gasto',
    monto                   DECIMAL(10, 2) NOT NULL COMMENT 'Monto del gasto',
    categoria               VARCHAR(127) COMMENT 'Categoría del gasto (insumos, servicios, mantenimiento, etc.)',
    insumo_id               INT COMMENT 'ID del insumo relacionado (si aplica), referencia de la tabla admi_insumo.id_insumo',
    proveedor               VARCHAR(255) COMMENT 'Proveedor o entidad a la que se pagó',
    fecha_gasto             DATE NOT NULL COMMENT 'Fecha en que se realizó el gasto',
    metodo_pago             VARCHAR(63) COMMENT 'Método de pago utilizado',
    factura_numero          VARCHAR(127) COMMENT 'Número de factura o comprobante',
    estado                  VARCHAR(1) DEFAULT 'A' COMMENT 'Estado: A= Activo, I= Inactivo',
    usr_creacion            VARCHAR(63) NOT NULL COMMENT 'Usuario que creó el registro',
    fe_creacion             TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha de creación',
    usr_ult_modificacion    VARCHAR(63) COMMENT 'Usuario que modificó por última vez el registro',
    fe_ult_modificacion     TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de última modificación',
    INDEX idx_fecha_gasto (fecha_gasto),
    INDEX idx_categoria (categoria),
    INDEX idx_id_insumo (insumo_id),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- AGREGAR FOREIGN KEYS DESPUÉS DE CREAR TODAS LAS TABLAS
-- =====================================================

-- Agregar foreign keys a info_ventas
ALTER TABLE info_ventas 
ADD CONSTRAINT fk_ventas_usuario 
FOREIGN KEY (usuario_id) REFERENCES admi_usuario(id_usuario) ON DELETE SET NULL;

ALTER TABLE info_ventas 
ADD CONSTRAINT fk_ventas_producto 
FOREIGN KEY (producto_id) REFERENCES admi_producto(id_producto) ON DELETE RESTRICT;

-- Agregar foreign key a info_gastos
ALTER TABLE info_gastos 
ADD CONSTRAINT fk_gastos_insumo 
FOREIGN KEY (insumo_id) REFERENCES admi_insumo(id_insumo) ON DELETE SET NULL;
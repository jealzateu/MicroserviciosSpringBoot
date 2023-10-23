CREATE DATABASE cliente_persona_db;
CREATE DATABASE cuenta_movimiento_db;

-- Selección de la base de datos
USE cliente_persona_db;

CREATE TABLE personas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(10) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(255) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    clienteId VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    estado TINYINT(1) NOT NULL,
    persona_id BIGINT,
    FOREIGN KEY (persona_id) REFERENCES personas(id)
);

-- Selección de la base de datos
USE cuenta_movimiento_db;

CREATE TABLE cuentas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(255) NOT NULL,
    tipo_cuenta VARCHAR(255) NOT NULL,
    saldo_inicial INT NOT NULL,
    estado TINYINT(1) NOT NULL
);

CREATE TABLE movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    tipo_movimiento VARCHAR(255) NOT NULL,
    valor INT NOT NULL,
    saldo INT NOT NULL,
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

CREATE TABLE transacciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME,
    descripcion VARCHAR(255),
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

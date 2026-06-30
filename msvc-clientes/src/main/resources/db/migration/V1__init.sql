CREATE TABLE IF NOT EXISTS clientes (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        rut VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    ciudad VARCHAR(255),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    fecha_registro DATE,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    tipo_cliente VARCHAR(50) DEFAULT 'REGULAR'
    );
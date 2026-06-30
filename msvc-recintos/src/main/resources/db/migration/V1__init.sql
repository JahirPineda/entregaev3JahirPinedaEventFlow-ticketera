CREATE TABLE IF NOT EXISTS recintos (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL,
    imagen_url VARCHAR(255),
    activo BOOLEAN NOT NULL DEFAULT TRUE
    );
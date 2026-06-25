CREATE TABLE IF NOT EXISTS categorias (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    imagen_url VARCHAR(255),
    activo BOOLEAN NOT NULL DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS eventos (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(1000),
    fecha DATE NOT NULL,
    hora TIME,
    imagen_url VARCHAR(255),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    categoria_id INT,
    recinto_id INT
    );
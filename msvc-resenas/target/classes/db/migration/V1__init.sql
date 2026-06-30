CREATE TABLE IF NOT EXISTS resenas (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       puntuacion INT NOT NULL,
                                       comentario VARCHAR(1000),
    fecha DATETIME,
    cliente_id INT,
    evento_id INT
    );

CREATE TABLE IF NOT EXISTS preguntas_frecuentes (
                                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                                    pregunta VARCHAR(500) NOT NULL,
    respuesta VARCHAR(1000) NOT NULL,
    categoria VARCHAR(100),
    orden INT NOT NULL DEFAULT 0,
    activo BOOLEAN NOT NULL DEFAULT TRUE
    );
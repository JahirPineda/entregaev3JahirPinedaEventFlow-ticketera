CREATE TABLE IF NOT EXISTS exponentes (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    biografia VARCHAR(1000),
    nacionalidad VARCHAR(100),
    imagen_url VARCHAR(255),
    redes_sociales VARCHAR(255),
    evento_id INT
    );
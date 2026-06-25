CREATE TABLE IF NOT EXISTS precios (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       tipo_entrada VARCHAR(50) NOT NULL,
    valor DOUBLE NOT NULL,
    stock INT NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    evento_id INT
    );
CREATE TABLE IF NOT EXISTS tickets_generados (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 codigo_qr VARCHAR(255) UNIQUE,
    fecha_emision DATETIME,
    usado BOOLEAN NOT NULL DEFAULT FALSE,
    tipo_entrada VARCHAR(50),
    orden_id INT,
    cliente_id INT,
    evento_id INT,
    vendedor_id INT
    );
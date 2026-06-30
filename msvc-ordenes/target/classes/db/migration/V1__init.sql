CREATE TABLE IF NOT EXISTS mis_ordenes (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           fecha_compra DATETIME,
                                           total DOUBLE NOT NULL,
                                           estado VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
    metodo_pago VARCHAR(50),
    cliente_id INT,
    evento_id INT
    );
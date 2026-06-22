INSERT INTO categorias (nombre, descripcion, imagen_url, activo) VALUES
                                                                     ('Música', 'Conciertos y festivales de música en vivo', 'https://img.com/musica.jpg', true),
                                                                     ('Deportes', 'Eventos deportivos y competencias', 'https://img.com/deportes.jpg', true),
                                                                     ('Teatro', 'Obras de teatro y espectáculos en vivo', 'https://img.com/teatro.jpg', true),
                                                                     ('Tecnología', 'Conferencias y eventos de tecnología', 'https://img.com/tech.jpg', true),
                                                                     ('Gastronomía', 'Ferias y festivales gastronómicos', 'https://img.com/gastro.jpg', true);

INSERT INTO eventos (nombre, descripcion, fecha, hora, imagen_url, activo, categoria_id, recinto_id) VALUES
                                                                                                         ('Festival Rock 2024', 'Gran festival de rock con bandas nacionales e internacionales', '2024-08-15', '18:00:00', 'https://img.com/rock.jpg', true, 1, 1),
                                                                                                         ('Copa Chile Fútbol', 'Final de la Copa Chile entre equipos nacionales', '2024-09-20', '20:00:00', 'https://img.com/futbol.jpg', true, 2, 2),
                                                                                                         ('Hamlet', 'Obra clásica de Shakespeare interpretada por compañía nacional', '2024-10-05', '19:30:00', 'https://img.com/hamlet.jpg', true, 3, 3),
                                                                                                         ('Tech Summit 2024', 'Conferencia internacional de tecnología e innovación', '2024-11-10', '09:00:00', 'https://img.com/tech.jpg', true, 4, 4),
                                                                                                         ('Festival Gastronómico', 'Feria de gastronomía con los mejores chefs del país', '2024-12-01', '12:00:00', 'https://img.com/gastro.jpg', true, 5, 5);
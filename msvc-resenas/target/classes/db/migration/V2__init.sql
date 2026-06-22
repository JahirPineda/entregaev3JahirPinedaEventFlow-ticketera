INSERT INTO resenas (puntuacion, comentario, fecha, cliente_id, evento_id) VALUES
                                                                               (5, 'Excelente evento, muy bien organizado', '2024-08-16 20:00:00', 1, 1),
                                                                               (4, 'Muy bueno, aunque la acústica podría mejorar', '2024-08-16 21:00:00', 2, 1),
                                                                               (5, 'Partido increíble, gran ambiente', '2024-09-21 22:00:00', 3, 2),
                                                                               (3, 'Regular, esperaba más del evento', '2024-10-06 21:00:00', 5, 3),
                                                                               (5, 'Obra magistral, actuaciones impecables', '2024-10-06 22:00:00', 4, 3);

INSERT INTO preguntas_frecuentes (pregunta, respuesta, categoria, orden, activo) VALUES
                                                                                     ('¿Cómo puedo comprar entradas?', 'Puedes comprar entradas desde nuestra plataforma web o app móvil usando tarjeta o transferencia', 'Compras', 1, true),
                                                                                     ('¿Puedo devolver mi entrada?', 'Las entradas son no reembolsables salvo cancelación del evento por parte del organizador', 'Pagos', 2, true),
                                                                                     ('¿Dónde veo mi ticket QR?', 'Tu ticket QR se envía al correo registrado y también está disponible en tu perfil', 'Tickets', 3, true),
                                                                                     ('¿Qué pasa si pierdo mi QR?', 'Puedes recuperarlo iniciando sesión en tu cuenta y descargándolo nuevamente', 'Tickets', 4, true),
                                                                                     ('¿Hay descuento para estudiantes?', 'Sí, ofrecemos entradas con descuento para estudiantes con credencial vigente', 'Pagos', 5, true);
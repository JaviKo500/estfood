

INSERT INTO marca (nombre_marca) VALUES  ('Rufles');
INSERT INTO marca (nombre_marca) VALUES  ('Doritos');
INSERT INTO marca (nombre_marca) VALUES  ('Coca cola');

INSERT INTO presentacion (nombre_presentacion) VALUES  ('35gr');
INSERT INTO presentacion (nombre_presentacion) VALUES  ('50gr');
INSERT INTO presentacion (nombre_presentacion) VALUES  ('170ml');


INSERT INTO zona (nombre_zona) VALUES  ('izquierda');
INSERT INTO zona (nombre_zona) VALUES  ('bodega');
INSERT INTO zona (nombre_zona) VALUES  ('cocina');

INSERT INTO producto (codigo_producto, descripcion_producto, estado_producto, fecha_ingreso, img_producto, menu_cliente_producto, precio_venta_producto, stock_producto, id_marca, id_presentacion, id_zona) VALUES  ('001', 'papas con salsa', true, '2022-08-08', '', false, 2, 5, 1, 1, 1);

INSERT INTO producto (codigo_producto, descripcion_producto, estado_producto, fecha_ingreso, img_producto, menu_cliente_producto, precio_venta_producto, stock_producto, id_marca, id_presentacion, id_zona) VALUES  ('002', 'Doritos dinamita', true, '2022-10-25', '', false, 5.6, 50, 2, 2, 2);

INSERT INTO producto (codigo_producto, descripcion_producto, estado_producto, fecha_ingreso, img_producto, menu_cliente_producto, precio_venta_producto, stock_producto, id_marca, id_presentacion, id_zona) VALUES  ('003', 'Cola', true, '2022-10-23', '', false, 1.20, 10, 3, 3, 3);

INSERT INTO detalle_proveedor (cuidad_detalle_proveedor, descripcion_detalle_proveedor, fecha_ingreso_detalle_proveedor) VALUES ('Cuenca', 'venta de snaks', '2022-08-03');

INSERT INTO detalle_proveedor (cuidad_detalle_proveedor, descripcion_detalle_proveedor, fecha_ingreso_detalle_proveedor) VALUES ('Guayaquil', 'venta de Embutidos', '2022-10-21');

INSERT INTO detalle_proveedor (cuidad_detalle_proveedor, descripcion_detalle_proveedor, fecha_ingreso_detalle_proveedor) VALUES ('Cuenca', 'venta de vegetales', '2022-09-15');

INSERT INTO proveedor (direccion_proveedor, email_proveedor, estado_proveedor, movil_proveedor, nombre_proveedor, ruc_proveedor, telefono_proveedor, id_detalle_proveedor) VALUES ('checa', 'doritos@gmail.com', true, '0987535645', 'Doritos', '0105566046001', '', 1 );

INSERT INTO proveedor (direccion_proveedor, email_proveedor, estado_proveedor, movil_proveedor, nombre_proveedor, ruc_proveedor, telefono_proveedor, id_detalle_proveedor) VALUES ('Lamar 5-56', 'piggiscuenca@gmail.com', true, '0967607728', 'Piggis', '0107081580001', '4259865', 2 );

INSERT INTO proveedor (direccion_proveedor, email_proveedor, estado_proveedor, movil_proveedor, nombre_proveedor, ruc_proveedor, telefono_proveedor, id_detalle_proveedor) VALUES ('Ricaurte', 'megamaxi2022@gmail.com', true, '0993728565', 'Megamaxi', '0150399327001', '', 3 );

INSERT INTO forma_pago (nombre_forma_pago) VALUES ('Efectivo');
INSERT INTO forma_pago (nombre_forma_pago) VALUES ('Crédito');

INSERT INTO tipo_cliente (descripcion_tipo_cliente) VALUES ('Docente');
INSERT INTO tipo_cliente (descripcion_tipo_cliente) VALUES ('Estudiante');
INSERT INTO tipo_cliente (descripcion_tipo_cliente) VALUES ('Externo');




INSERT INTO persona (nombres_persona, apellidos_persona, cedula_persona, email_persona) VALUES  ('Juan', 'Barrera', '0107081580', 'juan32barrera@gmail.com');
INSERT INTO persona (nombres_persona, apellidos_persona, cedula_persona, email_persona) VALUES  ('Beatriz', 'Velasquez', '0103976031', 'velasquez434B@gmail.com');
INSERT INTO persona (nombres_persona, apellidos_persona, cedula_persona, email_persona) VALUES  ('Pedro', 'Martinez', '0150399327', 'P1998Martinez@gmail.com');

INSERT INTO cliente (estado_cliente, telefono_cliente, id_persona, id_tipo_cliente) VALUES (true, '0965201148', 1, 2 );
INSERT INTO cliente (estado_cliente, telefono_cliente, id_persona, id_tipo_cliente) VALUES (true, '0965852323', 2, 1 );
INSERT INTO cliente (estado_cliente, telefono_cliente, id_persona, id_tipo_cliente) VALUES (true, '0965853210', 3, 3 );

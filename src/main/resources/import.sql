

INSERT INTO marca (nombre_marca) VALUES  ('Rufles');

INSERT INTO presentacion (nombre_presentacion) VALUES  ('35gr');

INSERT INTO zona (nombre_zona) VALUES  ('izquierda');

INSERT INTO producto (codigo_producto, descripcion_producto, estado_producto, fecha_ingreso, img_producto, menu_cliente_producto, precio_venta_producto, stock_producto, id_marca, id_presentacion, id_zona) VALUES  ('001', 'papas con salsa', true, '2022-08-08', '', false, 2, 5, 1, 1, 1);

INSERT INTO detalle_proveedor (cuidad_detalle_proveedor, descripcion_detalle_proveedor, fecha_ingreso_detalle_proveedor) VALUES ('Cuenca', 'venta de snaks', '2022-08-03');

INSERT INTO proveedor (direccion_proveedor, email_proveedor, estado_proveedor, movil_proveedor, nombre_proveedor, ruc_proveedor, telefono_proveedor, id_detalle_proveedor) VALUES ('checa', 'doritos@gmail.com', true, '0987535645', 'Doritos', '0105566046001', '', 1 );


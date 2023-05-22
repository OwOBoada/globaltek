/* FACTURAS  yyyy-MM-dd*/
INSERT INTO facturas (numero_Factura, create_at, tipo_Pago, documento_Cliente, nombre_Cliente, sub_Total, descuento, iva, total_Descuento, total_Impuesto, total) VALUES(1001, '2018-12-09', 'contado', 80225444, 'Juan Perez', 100000, 0, 19, 0, 19000, 119000);
INSERT INTO facturas (numero_Factura, create_at, tipo_Pago, documento_Cliente, nombre_Cliente, sub_Total, descuento, iva, total_Descuento, total_Impuesto, total) VALUES(1002, '2018-12-10', 'credito', 80225777, 'Pedro Hernandez', 200000, 10, 19, 20000, 34200, 214200);
INSERT INTO facturas (numero_Factura, create_at, tipo_Pago, documento_Cliente, nombre_Cliente, sub_Total, descuento, iva, total_Descuento, total_Impuesto, total) VALUES(1003, '2018-12-11', 'contado', 80225666, 'Mario Duarte', 300000, 5, 19, 15000, 54150, 339150);

INSERT INTO productos (nombre, precio_Unitario, create_at) VALUES('Camisa', 25000, NOW());
INSERT INTO productos (nombre, precio_Unitario, create_at) VALUES('Pantalon', 80000, NOW());
INSERT INTO productos (nombre, precio_Unitario, create_at) VALUES('Zapatos', 140000, NOW());
INSERT INTO productos (nombre, precio_Unitario, create_at) VALUES('Tenis', 160000, NOW());
INSERT INTO productos (nombre, precio_Unitario, create_at) VALUES('Falda', 70000, NOW());
INSERT INTO productos (nombre, precio_Unitario, create_at) VALUES('Blusa', 75000, NOW());


INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(4, 6, 2);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 4, 3);
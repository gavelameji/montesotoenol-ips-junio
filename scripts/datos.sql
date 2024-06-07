-- Clientes.

INSERT INTO CLIENTE (dni_cliente, nombre_cliente, direccion_cliente, telefono_cliente)
VALUES ('1', 'José Luis Perales', 'C/ Moreno, Nº22, 1ºA', '123456789');

INSERT INTO CLIENTE (dni_cliente, nombre_cliente, direccion_cliente, telefono_cliente)
VALUES ('2', 'Marta Del Río', 'C/ Fernando Alonso, 33, 14ºA', '787378462');

-- Oficinas
INSERT INTO oficina (id_oficina, ciudad_oficina, direccion_oficina)
VALUES ('1', 'Villablino', 'C/ Colominas, 45');

INSERT INTO oficina (id_oficina, ciudad_oficina, direccion_oficina)
VALUES ('2', 'Ponferrada', 'C/ Las Ollas, 12');

INSERT INTO oficina (id_oficina, ciudad_oficina, direccion_oficina)
VALUES ('3', 'Cerredo', 'C/ San Miguel, 87');

INSERT INTO oficina (id_oficina, ciudad_oficina, direccion_oficina)
VALUES ('4', 'Cangas Del Narcea', 'C/ Dos Amigos, 78');

-- Paquetes

INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete, 
					 recoger_a_domicilio, estado_paquete, id_ruta,
					 direccion_origen_paquete, direccion_destino_paquete,
					 dni_cliente, id_oficina)
VALUES ('1', 'paquete1', '2024-06-01', 
		true, 'pendiente', NULL,
		'C/ Moreno, Nº22, 1ºA', 'C/ Ibias, 1, 2ºDcha',
		'1', NULL);
		
INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete, 
					 recoger_a_domicilio, estado_paquete, id_ruta,
					 direccion_origen_paquete, direccion_destino_paquete,
					 dni_cliente, id_oficina)
VALUES ('2', 'paquete2', '2024-06-03', 
		true, 'recibido', NULL,
		'C/ Moreno, Nº22, 1ºA', 'C/ Cerredo, 11, 2ºA',
		'1', NULL);
		
INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete, 
					 recoger_a_domicilio, estado_paquete, id_ruta,
					 direccion_origen_paquete, direccion_destino_paquete,
					 dni_cliente, id_oficina)
VALUES ('3', 'paquete3', '2024-06-02', 
		true, 'en ruta', NULL,
		'C/ Moreno, Nº22, 1ºA', 'C/ San Chicho, 2, 4ºDcha',
		'1', NULL);

INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete, 
					 recoger_a_domicilio, estado_paquete, id_ruta,
					 direccion_origen_paquete, direccion_destino_paquete,
					 dni_cliente, id_oficina)
VALUES ('4', 'paquete4', '2024-05-04', 
		FALSE, 'recibido', NULL,
		'C/ Fernando Alonso, 33, 14ºA', 'C/ Mar Azul, s/n',
		'2', NULL);
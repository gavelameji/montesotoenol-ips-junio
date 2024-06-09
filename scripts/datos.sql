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

-- Almacenes
INSERT INTO almacen (id_almacen, ciudad_almacen, direccion_almacen)
VALUES ('1', 'Degaña', 'C/ Rey Pelayo, 18');

INSERT INTO almacen (id_almacen, ciudad_almacen, direccion_almacen)
VALUES ('2', 'San Antolín De Ibias', 'C/ Menendez, 34');

INSERT INTO almacen (id_almacen, ciudad_almacen, direccion_almacen)
VALUES ('3', 'Mieres', 'C/ Carlos III, 27');

INSERT INTO almacen (id_almacen, ciudad_almacen, direccion_almacen)
VALUES ('4', 'Posada De Rengos', 'C/ Las Caldas, 35');

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
		'C/ Vicente Sánchez, Nº23, 1ºA', 'C/ Moreda, 11, 2ºA',
		'2', NULL);
		
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
VALUES ('4', 'paquete4', '2024-06-02', 
		true, 'en ruta', NULL,
		'C/ Ruta Del Alba, Nº3, 1ºC', 'C/ Las Vegas, 2, 4ºDcha',
		'1', NULL);
		
INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete, 
					 recoger_a_domicilio, estado_paquete, id_ruta,
					 direccion_origen_paquete, direccion_destino_paquete,
					 dni_cliente, id_oficina)
VALUES ('5', 'paquete5', '2024-06-01', 
		true, 'en ruta', NULL,
		'C/ Los Arrudos, Nº3, 1ºD', 'C/ Salinas, 2, 4ºDcha',
		'1', NULL);
		
INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete, 
					 recoger_a_domicilio, estado_paquete, id_ruta,
					 direccion_origen_paquete, direccion_destino_paquete,
					 dni_cliente, id_oficina)
VALUES ('6', 'paquete6', '2024-03-02', 
		true, 'en ruta', NULL,
		'C/ Las Xanas, Nº6, 2ºA', 'C/ Macarrón, 2, 4ºDcha',
		'1', NULL);
		
INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete, 
					 recoger_a_domicilio, estado_paquete, id_ruta,
					 direccion_origen_paquete, direccion_destino_paquete,
					 dni_cliente, id_oficina)
VALUES ('7', 'paquete7', '2024-01-12', 
		true, 'en ruta', NULL,
		'C/ San Cosme, Nº4, 5ºA', 'C/ Villaranda, 2, 4ºDcha',
		'1', NULL);
		
INSERT INTO paquete (id_paquete, descripcion_paquete, fecha_emision_paquete, 
					 recoger_a_domicilio, estado_paquete, id_ruta,
					 direccion_origen_paquete, direccion_destino_paquete,
					 dni_cliente, id_oficina)
VALUES ('8', 'paquete8', '2024-05-04', 
		FALSE, 'recibido', NULL,
		'C/ Fernando Alonso, 33, 14ºA', 'C/ Mar Azul, s/n',
		'1', NULL);
		
		
-- Localizadores

INSERT INTO localizador (id_localizador, id_paquete)
VALUES ('1', '4');

INSERT INTO localizador (id_localizador, id_paquete)
VALUES ('2', '5');

INSERT INTO localizador (id_localizador, id_paquete)
VALUES ('3', '6');

INSERT INTO localizador (id_localizador, id_paquete)
VALUES ('4', '7');


-- Ubicaciones

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('1', '1', '1', false);

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('2', '2', '1', false);

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('3', '3', '1', true);


INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('4', '3', '2', false);

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('5', '2', '2', false);

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('6', '1', '2', true);


INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('7', '3', '3', false);

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('8', '2', '3', true);


INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('9', '1', '4', false);

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('10', '2', '4', false);

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('11', '3', '4', false);

INSERT INTO ubicacion (id_ubicacion, id_almacen, id_localizador, es_actual)
VALUES ('12', '4', '4', true);
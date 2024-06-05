-- Cliente
DROP TABLE IF EXISTS cliente;
CREATE TABLE cliente(
    id_cliente VARCHAR(8),
    nombre_cliente VARCHAR(30) NOT NULL,
    direccion_cliente VARCHAR(30) NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente)
);

-- Oficina
DROP TABLE IF EXISTS oficina;
CREATE TABLE oficina(
    id_oficina VARCHAR(8),
    nombre_oficina VARCHAR(30) NOT NULL,
    direccion_oficina VARCHAR(30) NOT NULL,
    CONSTRAINT pk_oficina PRIMARY KEY (id_oficina)
);

-- Almacen
DROP TABLE IF EXISTS almacen;
CREATE TABLE almacen(
    id_almacen VARCHAR(8),
    nombre_almacen VARCHAR(30) NOT NULL,
    direccion_almacen VARCHAR(30) NOT NULL,
    CONSTRAINT pk_almacen PRIMARY KEY (id_almacen)
);

-- Vehiculo
DROP TABLE IF EXISTS vehiculo;
CREATE TABLE vehiculo(
    id_vehiculo VARCHAR(8),
    tipo_vehiculo VARCHAR(10),
    capacidad_vehiculo INT,
    CONSTRAINT pk_vehiculo PRIMARY KEY (id_vehiculo)
);

-- Ruta
DROP TABLE IF EXISTS ruta;
CREATE TABLE ruta(
    id_ruta VARCHAR(8),
    origen_ruta VARCHAR(30),
    destino_ruta VARCHAR(30),
    distancia FLOAT,
    duracion_estimada_ruta TIME,
    id_vehiculo VARCHAR(8),
    estado_ruta VARCHAR(20),
    CONSTRAINT pk_ruta PRIMARY KEY (id_ruta),
    CONSTRAINT fk_ruta_vehiculo FOREIGN KEY (id_vehiculo) REFERENCES vehiculo (id_vehiculo)
);

-- Paquete
DROP TABLE IF EXISTS paquete;
CREATE TABLE paquete(
    id_paquete VARCHAR(8),
    descripcion_paquete VARCHAR(30) NOT NULL,
    a_domicilio_paquete BOOLEAN,
    direccion_destino_paquete VARCHAR(30) NOT NULL,
    id_ruta VARCHAR(8),
    CONSTRAINT pk_paquete PRIMARY KEY (id_paquete),
    CONSTRAINT fk_paquete_ruta FOREIGN KEY (id_ruta) REFERENCES ruta (id_ruta)
);

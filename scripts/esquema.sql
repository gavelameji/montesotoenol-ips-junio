

DROP TABLE IF EXISTS paquete;
DROP TABLE IF EXISTS ruta;
DROP TABLE IF EXISTS almacen;
DROP TABLE IF EXISTS oficina;
DROP TABLE IF EXISTS cliente;


-- Cliente
CREATE TABLE cliente(
    dni_cliente VARCHAR(8),
    nombre_cliente VARCHAR(30) NOT NULL,
    direccion_cliente VARCHAR(30) NOT NULL,
    telefono_cliente VARCHAR(9),
    CONSTRAINT pk_cliente PRIMARY KEY (dni_cliente)
);

-- Oficina
CREATE TABLE oficina(
    id_oficina VARCHAR(8),
    nombre_oficina VARCHAR(30) NOT NULL,
    direccion_oficina VARCHAR(30) NOT NULL,
    CONSTRAINT pk_oficina PRIMARY KEY (id_oficina)
);

-- Almacen
CREATE TABLE almacen(
    id_almacen VARCHAR(8),
    nombre_almacen VARCHAR(30) NOT NULL,
    direccion_almacen VARCHAR(30) NOT NULL,
    CONSTRAINT pk_almacen PRIMARY KEY (id_almacen)
);

-- Ruta
CREATE TABLE ruta(
    id_ruta VARCHAR(8),
    origen_ruta VARCHAR(30),
    destino_ruta VARCHAR(30),
    distancia FLOAT,
    duracion_estimada_ruta TIME,
    id_vehiculo VARCHAR(8),
    estado_ruta VARCHAR(20),
    CONSTRAINT pk_ruta PRIMARY KEY (id_ruta)
);

-- Paquete
CREATE TABLE paquete(
    id_paquete VARCHAR(8),
    descripcion_paquete VARCHAR(30) NOT NULL,
    recoger_a_domicilio BOOLEAN,
    fecha_emision_paquete DATE,
    direccion_origen_paquete VARCHAR(30),
    direccion_destino_paquete VARCHAR(30),
    estado_paquete VARCHAR(15),
    id_ruta VARCHAR(8),
    dni_cliente VARCHAR(8),
    id_oficina VARCHAR(8),
    CONSTRAINT pk_paquete PRIMARY KEY (id_paquete),
    CONSTRAINT fk_paquete_ruta FOREIGN KEY (id_ruta) REFERENCES ruta (id_ruta),
    CONSTRAINT fk_paquete_cliente FOREIGN KEY (dni_cliente) REFERENCES cliente (dni_cliente),
    CONSTRAINT fk_paquete_oficina FOREIGN KEY (id_oficina) REFERENCES oficina (id_oficina)
);

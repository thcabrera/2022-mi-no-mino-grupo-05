INSERT INTO persona (id, apellido, nombre, documento, tipo_doc)
values(3, 'Cabrera', 'Thiago', 43988887, 'DNI');

-- la contraseña deshasheada es "dds2022"
INSERT INTO usuario (nombre_usuario, contrasenia, actor_id, rol)
	values ('ddsdreamteam', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', 3, 'PERSONA'),
			('admindreamteam', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', NULL, 'ADMINISTRADOR');

INSERT INTO clasificacion (descripcion) values ("Ministerio");
INSERT INTO clasificacion (descripcion) values ("Universidad");
INSERT INTO clasificacion (descripcion) values ("Escuela");
INSERT INTO clasificacion (descripcion) values ("Empresa del sector primario");
INSERT INTO clasificacion (descripcion) values ("Empresa del sector secundario");

INSERT INTO tipo_org (descripcion) VALUES ('Gubernamental');
INSERT INTO tipo_org (descripcion) VALUES ('ONG');
INSERT INTO tipo_org (descripcion) VALUES ('Empresa');
INSERT INTO tipo_org (descripcion) VALUES ('Institución');

INSERT INTO direccion (id, calle, altura, localidad_id, municipio_id, provincia_id)
	VALUES(7, 'LAVALLE', 4039, 1, 77, 1),
    (8, 'MAGALLANES', 4038, 1, 77, 1);

INSERT INTO organizacion (id, nombre, razon_social, clasificacion_id, tipo_org_id, direccion_id)
	values(1, 'Mercadopago', '3527-3859', 3, 3, NULL),
    (2, 'UTN', '3527-4029', 3, 3, NULL);

INSERT INTO area (id, descripcion, org_id)
	values(1, 'VENTAS', 1),
	(2, 'RRHH', 1),
    (3, 'PAGOS', 1),
    (4, 'COMERCIO EXTERIOR', 1),
    (5, 'PUERTO', 2);

-- metemos a thiago en el area PUERTO de la UTN
INSERT INTO area_persona values(5,3);

INSERT INTO tipo_linea (consumo, descripcion) values (2.5, "Colectivo");
INSERT INTO tipo_linea (consumo, descripcion) values (5, "Tren");
INSERT INTO tipo_linea (consumo, descripcion) values (4, "Subte");

INSERT INTO tipo_particular (consumo, descripcion) values (1, "Auto");
INSERT INTO tipo_particular (consumo, descripcion) values (0.9, "Moto");
INSERT INTO tipo_particular (consumo, descripcion) values (1.25, "Camioneta");
INSERT INTO tipo_particular (consumo, descripcion) values (2, "Camión");

INSERT INTO tipo_servicio (descripcion) VALUES ('Remis');
INSERT INTO tipo_servicio (descripcion) VALUES ('Uber');
INSERT INTO tipo_servicio (descripcion) VALUES ('Didi');
INSERT INTO tipo_servicio (descripcion) VALUES ('Taxi');
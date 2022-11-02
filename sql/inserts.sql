-- tablas independientes

INSERT INTO tipo_consumo (factor_emision, unidad, descripcion, tipo_actividad) VALUES
-- COMBUSTIÓN FIJA
(1.5, 'M3', 'GAS NATURAL', 'COMBUSTION_FIJA'),
(1.5, 'LT', 'DIESEL', 'COMBUSTION_FIJA'),
(1.5, 'LT', 'GASOIL', 'COMBUSTION_FIJA'),
(1.5, 'LT', 'KEROSENE', 'COMBUSTION_FIJA'),
(1.5, 'LT', 'FUEL OIL', 'COMBUSTION_FIJA'),
(1.5, 'LT', 'NAFTA', 'COMBUSTION_FIJA'),
(1.5, 'KG', 'CARBÓN', 'COMBUSTION_FIJA'),
(1.5, 'KG', 'CARBÓN DE LEÑA', 'COMBUSTION_FIJA'),
(1.5, 'KG', 'LEÑA', 'COMBUSTION_FIJA'),
-- COMBUSTIÓN MÓVIL
(1.5, 'LTS', 'GASOIL', 'COMBUSTION_MOVIL'),
(1.5, 'LTS', 'GNC', 'COMBUSTION_MOVIL'),
(1.5, 'LTS', 'NAFTA', 'COMBUSTION_MOVIL'),
-- ELECTRICIDAD
(1.5, 'KWH', 'ELECTRICIDAD', 'ELECTRICIDAD');

INSERT INTO medio_transporte(descripcion, factor_emision) values
	('CAMIÓN DE CARGA', 5.0),
    ('UTILITARIO LIVIANO', 5.0);

INSERT INTO clasificacion (descripcion) values
("Ministerio"),
("Universidad"),
("Escuela"),
("Empresa del sector primario"),
("Empresa del sector secundario");

INSERT INTO tipo_org (descripcion) VALUES
('Gubernamental'),
('ONG'),
('Empresa'),
('Institución');

INSERT INTO tipo_linea (consumo, descripcion) values
(2.5, "Colectivo"),
(5, "Tren"),
(4, "Subte");

INSERT INTO tipo_particular (consumo, descripcion) values
(1, "Auto"),
(0.9, "Moto"),
(1.25, "Camioneta"),
(2, "Camión");

INSERT INTO tipo_servicio (descripcion, consumo) VALUES
	('Remis', 1.5 ),
	('Uber', 1.5),
	('Didi', 1.5),
	('Taxi', 1.8);

INSERT INTO combustible (consumo, descripcion) VALUES
    (1.5, 'GNC'),
	(2.0, 'NAFTA'),
    (0.8, 'ELÉCTRICO'),
    (1.2, 'GASOIL');

-- tablas dependientes

INSERT INTO persona (id, apellido, nombre, documento, tipo_doc) values
(3, 'Cabrera', 'Thiago', 43988887, 'DNI'),
(98, 'Broker - Campi', 'Lucas', 25636368, 'DNI');

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
INSERT INTO area_persona values
 	(5,3),
    (4,3),
    (2,3);

INSERT INTO trayecto (org_id, persona_id) values
	(2,3),
    (1,3),
    (1,3);

-- la contraseña deshasheada es "dds2022"
INSERT INTO usuario (nombre_usuario, contrasenia, actor_id, rol)
	values ('ddsdreamteam', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', 3, 'PERSONA'),
			('admindreamteam', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', NULL, 'ADMINISTRADOR'),
            ('orgdreamteam', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', 1, 'ORGANIZACION'),
			('lennySW', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', 3, 'ORGANIZACION'),
            ('lucascampi', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', 98, 'PERSONA');


INSERT INTO huella_de_carbono.linea 
		(id, descripcion, tipo_linea_id)
VALUES  (1, "132", 1 ), (2, "26" , 1 ), (3, "36" , 1 ),
        (4, "A" , 3 ),(5, "B" , 3 ),(6, "C" , 3 ),(7, "D" , 3 ),(8, "E" , 3 ),(9, "H" , 3 ),
        (10, "Mitre" , 2 ),(11, "Sarmiento" , 2 ),(12, "San Martin" , 2 );

INSERT huella_de_carbono.parada 
		(id, distancia_ant_parada, distancia_sig_parada, indice, nombre_parada , linea_id)
values  (1 , 400                  ,  300               ,  0    , "Av. La Plata",  1), -- 132
		(2 , 500                  ,  200               ,  0    , "Acoyte"      ,  1), -- 132
        (3 , 300                  ,  400               ,  0    , "Varela"      ,  1), -- 132
        (4 , 400                  ,  300               ,  0    , "Av. La Plata",  8), -- E
        (5 , 700                  ,  500               ,  0    , "San Pedrito",  8); -- E

/*
------------------------------SELECTS---------------------------
select * from tramo;
select * from trayecto;
select * from tramo_trayecto;
select * from tramo_limpio;
select * from tramo_contratado;
select * from tramo_particular;
select * from tramo_publico;
------------------------------DELETES----------------------------
delete from tramo_trayecto;
delete from tramo_limpio;
delete from tramo;
*/
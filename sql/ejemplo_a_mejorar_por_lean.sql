-- meto a thiaguin en comercio exterior de mercado pago
INSERT INTO area_persona values(4,3);

select * from trayecto
INSERT INTO direccion (id, calle, altura, localidad_id, municipio_id, provincia_id)
	VALUES(7, 'LAVALLE', 4039, 1, 77, 1),
    (8, 'MAGALLANES', 4038, 1, 77, 1);

INSERT INTO organizacion (id, nombre, razon_social, clasificacion_id, tipo_org_id, direccion_id)
	values  (1, 'Mercadopago', '3527-3859', 3, 3, 7),
   (2, 'UTN', '3527-4029', 3, 3, 8),
    (5, 'LennySW', '3527-4029', 3, 3, 8);


INSERT INTO area (id, descripcion, org_id)
	values(1, 'VENTAS', 1),
	(2, 'RRHH', 1),
    (3, 'PAGOS', 1),
    (4, 'COMERCIO EXTERIOR', 1),
    (5, 'PUERTO', 2);


-- metemos a thiago en el area PUERTO de la UTN
-- INSERT INTO area_persona values(5,3);
INSERT INTO area_persona values(5,3);
select * from area_persona;
select * from persona;
select * from actor;
select * from organizacion;

-- delete from area_persona;
select * from solicitud;
 -- delete from solicitud
INSERT INTO solicitud(id, estado, area_id, org_id, persona_id) values(1,"ENVIADA", 2, 1 , 4);
INSERT INTO solicitud(id, estado, area_id, org_id, persona_id) values(2,"ENVIADA", 3, 1 , 4);
INSERT INTO solicitud(id, estado, area_id, org_id, persona_id) values(3,"ENVIADA", 5, 2 , 3);

select * from organizacion;


INSERT INTO usuario (nombre_usuario, contrasenia, actor_id, rol) values('lennySW', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', 5, 'ORGANIZACION');

-- PERIDOCIDAD PARA MEDICIONES Y TRAMOS xd
INSERT INTO periodicidad (id, discriminador, anio, mes) values(1, "ANUAL", 2020, null);
INSERT INTO periodicidad (id, discriminador, anio, mes) values(2, "ANUAL", 2021, null);
INSERT INTO periodicidad (id, discriminador, anio, mes) values(3, "ANUAL", 2022, null);
INSERT INTO periodicidad (id, discriminador, anio, mes) values(4, "MENSUAL", 2022, 1);
INSERT INTO periodicidad (id, discriminador, anio, mes) values(5, "MENSUAL", 2022, 2);
INSERT INTO periodicidad (id, discriminador, anio, mes) values(6, "MENSUAL", 2022, 3);

-- --------ACTIVIDADES----------------------------
INSERT INTO tipoconsumo(id, factor_emision, unidad) values (1, 100,'kgCO2eq/kWh');
delete  from tipoconsumo;
select  * from tipoconsumo

INSERT INTO consumo(id, valor, tipo_consumo_id) values(1, 30 ,1);
select * from actividad;
delete  from consumo

select * from usuario;
select * from organizacion; --  llenySW id = 5
select * from persona;
select * from tipo_actividad;
select * from periodicidad

select * from organizacion;
select * from area;
select * from area_persona;

INSERT INTO usuario (nombre_usuario, contrasenia, actor_id, rol)
	values ('lean', '05bffb1eb29246b5e4351893e254f01f3fc336ae6c136c334fe7cfb00d00ce95', 3, 'PERSONA');
update usuario 
SET actor_id = 5
where nombre_usuario = 'lean'

UPDATE usuario
SET actor_id = 5
WHERE SalesOrderNum = 00061358 and WorkTicketNumber = 000933
INSERT INTO persona (id, apellido, nombre, documento, tipo_doc)
values(10, 'Leandro', 'Lienard', 43814124, 'DNI');
-- metemos a lean en sistemas
INSERT INTO area_persona(area_id, persona_id) values(6,5);
delete from persona where id = 5 

select * from area_persona;
select * from tramo;
select * from tramo_limpio


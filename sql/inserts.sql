INSERT INTO clasificacion (descripcion) values ("Ministerio");
INSERT INTO clasificacion (descripcion) values ("Universidad");
INSERT INTO clasificacion (descripcion) values ("Escuela");
INSERT INTO clasificacion (descripcion) values ("Empresa del sector primario");
INSERT INTO clasificacion (descripcion) values ("Empresa del sector secundario");

INSERT INTO tipo_org (descripcion) VALUES ('Gubernamental');
INSERT INTO tipo_org (descripcion) VALUES ('ONG');
INSERT INTO tipo_org (descripcion) VALUES ('Empresa');
INSERT INTO tipo_org (descripcion) VALUES ('Institución');

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

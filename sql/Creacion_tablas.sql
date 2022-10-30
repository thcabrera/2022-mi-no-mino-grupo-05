 -- DROP SCHEMA probando_script;
 -- CREATE SCHEMA probando_scrip;
-- SI NO EXISTEN EJECUTAR DESDE LA PRIMER LINEA DE CREATE (LINEA 86)
alter table consumo             drop foreign key FK_consumo_tipo_consumo_id;
alter table actividad           drop foreign key FK_actividad_org_id;
alter table actividad           drop foreign key FK_actividad_periodicidad_id;
alter table actividad           drop foreign key FK_actividad_medio_transporte_id;
alter table actividad           drop foreign key FK_actividad_consumo_id;
alter table agente_sectorial    drop foreign key FK_agente_sectorial_sector_id;
alter table area                drop foreign key FK_area_org_id;
alter table area_persona        drop foreign key FK_area_persona_persona_id;
alter table area_persona        drop foreign key FK_area_persona_area_id;
alter table contacto            drop foreign key FK_contacto_org_id;
alter table direccion           drop foreign key FK_direccion_localidad_id;
alter table direccion           drop foreign key FK_direccion_municipio_id;
alter table direccion           drop foreign key FK_direccion_provincia_id;
alter table huella_de_carbono   drop foreign key FK_huella_de_carbono_organizacion_id;
alter table linea               drop foreign key FK_linea_tipo_linea_id;
alter table localidad           drop foreign key FK_localidad_municipio_id;
alter table organizacion        drop foreign key FK_organizacion_clasificacion_id;
alter table organizacion        drop foreign key FK_organizacion_tipo_org_id;
alter table organizacion        drop foreign key FK_organizacion_direccion_id;
alter table parada              drop foreign key FK_parada_parada_id;
alter table sector              drop foreign key FK_sector_provincia_id;
alter table solicitud           drop foreign key FK_solicitud_area_id;
alter table solicitud           drop foreign key FK_solicitud_org_id;
alter table solicitud           drop foreign key FK_solicitud_persona_id;
alter table tramo_contratado    drop foreign key FK_tramo_contratado_direccion_fin_id;
alter table tramo_contratado    drop foreign key FK_tramo_contratado_direccion_inicio_id;
alter table tramo_contratado    drop foreign key FK_tramo_contratado_propietario_id;
alter table tramo_contratado    drop foreign key FK_tramo_contratado_tipo_servicio_id;
alter table tramo_contratado    drop foreign key FK_tramo_contratado_id;
alter table tramo_limpio        drop foreign key FK_tramo_limpio_direccion_fin_id;
alter table tramo_limpio        drop foreign key FK_tramo_limpio_direccion_inicio_id;
alter table tramo_limpio        drop foreign key FK_tramo_limpio_id;
alter table tramo_particular    drop foreign key FK_tramo_particular_direccion_fin_id;
alter table tramo_particular    drop foreign key FK_tramo_particular_direccion_inicio_id;
alter table tramo_particular    drop foreign key FK_tramo_particular_propietario_id;
alter table tramo_particular    drop foreign key FK_tramo_particular_tipo_combustible_id;
alter table tramo_particular    drop foreign key FK_tramo_particular_tipo_particular_id;
alter table tramo_particular    drop foreign key FK_tramo_particular_id;
alter table tramo_publico       drop foreign key FK_tramo_publico_linea_id;
alter table tramo_publico       drop foreign key FK_tramo_publico_parada_fin_id;
alter table tramo_publico       drop foreign key FK_tramo_publico_parada_inicio_id;
alter table tramo_publico       drop foreign key FK_tramo_publico_id;
alter table tramo_trayecto      drop foreign key FK_tramo_trayecto_tramo_id;
alter table tramo_trayecto      drop foreign key FK_tramo_trayecto_trayecto_id;
alter table trayecto            drop foreign key FK_trayecto_org_id;
alter table trayecto            drop foreign key FK_trayecto_persona_id;
    
    drop table if exists consumo; 
    drop table if exists medio_transporte; 
    drop table if exists tipo_consumo; 
    drop table if exists actividad; 
    drop table if exists agente_sectorial; 
    drop table if exists area; 
    drop table if exists area_persona; 
    drop table if exists clasificacion; 
    drop table if exists combustible; 
    drop table if exists contacto; 
    drop table if exists direccion; 
    drop table if exists huella_de_carbono; 
    drop table if exists linea; 
    drop table if exists localidad; 
    drop table if exists organizacion; 
    drop table if exists parada; 
    drop table if exists periodicidad; 
    drop table if exists persona;
    drop table if exists sector;
    drop table if exists solicitud; 
    drop table if exists tipo_linea; 
    drop table if exists tipo_org; 
    drop table if exists tipo_particular; 
    drop table if exists tipo_servicio; 
    drop table if exists tramo; 
    drop table if exists tramo_contratado; 
    drop table if exists tramo_limpio; 
    drop table if exists tramo_particular; 
    drop table if exists tramo_publico; 
    drop table if exists tramo_trayecto; 
    drop table if exists trayecto; 

    create table consumo (
        id integer not null auto_increment,
        valor double precision,
        tipo_consumo_id integer,
        primary key (id)
    );
 
    create table mediotransporte (
        id integer not null auto_increment,
        factor_emision double precision,
        primary key (id)
    );
 
    create table tipo_consumo (
        id integer not null auto_increment,
        factor_emision double precision,
        unidad varchar(255),
        primary key (id)
    );
 
    create table actividad (
        discriminador varchar(18) not null,
        id integer not null auto_increment,
        categoria varchar(255),
        distancia_media double precision,
        peso double precision,
        tipo_actividad varchar(255),
        org_id integer,
        periodicidad_id integer,
        medio_transporte_id integer,
        consumo_id integer,
        primary key (id)
    );
 
    create table agente_sectorial (
        id integer not null auto_increment,
        sector_id integer,
        primary key (id)
    );
 
    create table area (
        id integer not null auto_increment,
        descripcion varchar(255),
        org_id integer,
        primary key (id)
    );
 
    create table area_persona (
        area_id integer not null,
        persona_id integer not null,
        primary key(area_id, persona_id)
    );
 
    create table clasificacion (
        id integer not null auto_increment,
        descripcion varchar(255),
        primary key (id)
    );
 
    create table combustible (
        id integer not null auto_increment,
        consumo double precision,
        descripcion varchar(255),
        primary key (id)
    );
 
    create table contacto (
        discriminador varchar(31) not null,
        id integer not null auto_increment,
        contacto varchar(255),
        org_id integer,
        primary key (id)
    );
 
    create table direccion (
        id integer not null auto_increment,
        altura integer not null ,
        calle varchar(255) not null ,
        localidad_id integer,
        municipio_id integer not null ,
        provincia_id integer not null ,
        primary key (id)
    );
 
    create table huella_de_carbono (
        id integer not null auto_increment,
        fecha_medicion date not null,
        valor double precision not null ,
        organizacion_id integer not null,
        primary key (id)
    );
 
    create table linea (
        id integer not null auto_increment,
        descripcion varchar(255),
        tipo_linea_id integer,
        primary key (id)
    );
 
    create table localidad (
        id integer not null,
        descripcion varchar(255),
        numero_postal varchar(8),
        municipio_id integer,
        primary key (id)
    );
 
    create table organizacion (
        id integer not null auto_increment,
        nombre varchar(255),
        razon_social varchar(255),
        clasificacion_id integer,
        tipo_org_id integer,
        direccion_id integer,
        primary key (id)
    );
 
    create table parada (
        id integer not null auto_increment,
        distancia_ant_parada double precision,
        distancia_sig_parada double precision,
        indice integer,
        nombre_parada varchar(255),
        linea_id integer,
        primary key (id)
    );
 
    create table periodicidad (
        discriminador varchar(31) not null,
        id integer not null auto_increment,
        anio integer not null,
        mes integer,
        primary key (id)
    );
 
    create table persona (
        id integer not null auto_increment,
        apellido varchar(255),
        nombre varchar(255),
        documento integer,
        tipo_doc varchar(255),
        primary key (id)
    );
  
    create table sector (
        tipo varchar(10) not null,
        id integer not null auto_increment,
        descripcion varchar(255),
        provincia_id integer,
        primary key (id)
    );
 
    create table solicitud (
        id integer not null auto_increment,
        estado varchar(255),
        area_id integer not null,
        org_id integer not null,
        persona_id integer not null,
        primary key (id)
    );
 
    create table tipo_linea (
        id integer not null auto_increment,
        consumo double precision,
        descripcion varchar(255),
        primary key (id)
    );
 
    create table tipo_org (
        id integer not null auto_increment,
        descripcion varchar(255),
        primary key (id)
    );
 
    create table tipo_particular (
        id integer not null auto_increment,
        consumo double precision,
        descripcion varchar(255),
        primary key (id)
    );
 
    create table tipo_servicio (
        id integer not null auto_increment,
        descripcion varchar(255),
        primary key (id)
    );
 
    create table tramo (
        id integer not null auto_increment,
        tipo_tramo varchar(255),
        primary key (id)
    );

    create table tramo_contratado (
        consumo double precision,
        es_compartido bit,
        id integer not null,
        direccion_fin_id integer,
        direccion_inicio_id integer,
        propietario_id integer,
        tipo_servicio_id integer,
        primary key (id)
    );
 
    create table tramo_limpio (
        tipo varchar(255),
        id integer not null,
        direccion_fin_id integer,
        direccion_inicio_id integer,
        primary key (id)
    );
 
    create table tramo_particular (
        es_compartido bit,
        id integer not null,
        direccion_fin_id integer,
        direccion_inicio_id integer,
        propietario_id integer,
        tipo_combustible_id integer,
        tipo_particular_id integer,
        primary key (id)
    );
 
    create table tramo_publico (
        id integer not null,
        linea_id integer,
        parada_fin_id integer,
        parada_inicio_id integer,
        primary key (id)
    );
 
    create table tramo_trayecto (
        trayecto_id integer not null,
        tramo_id integer not null,
        primary key(trayecto_id, tramo_id)
    );
 
    create table trayecto (
        id integer not null auto_increment,
        org_id integer,
        persona_id integer,
        primary key (id)
    );
 
    alter table consumo 
        add constraint FK_consumo_tipo_consumo_id
        foreign key (tipo_consumo_id) 
        references tipo_consumo (id);
 
    alter table actividad 
        add constraint FK_actividad_org_id
        foreign key (org_id) 
        references organizacion (id);
 
    alter table actividad 
        add constraint FK_actividad_periodicidad_id
        foreign key (periodicidad_id) 
        references periodicidad (id);
 
    alter table actividad 
        add constraint FK_actividad_medio_transporte_id
        foreign key (medio_transporte_id) 
        references MedioTransporte (id);
 
    alter table actividad 
        add constraint FK_actividad_consumo_id
        foreign key (consumo_id) 
        references Consumo (id);
 
    alter table agente_sectorial 
        add constraint FK_agente_sectorial_sector_id
        foreign key (sector_id) 
        references sector (id);
 
    alter table area 
        add constraint FK_area_org_id
        foreign key (org_id) 
        references organizacion (id);
 
    alter table area_persona 
        add constraint FK_area_persona_persona_id
        foreign key (persona_id) 
        references persona (id),
        add constraint FK_area_persona_area_id
        foreign key (area_id) 
        references area (id);
  
    alter table contacto 
        add constraint FK_contacto_org_id
        foreign key (org_id) 
        references organizacion (id);
 
    alter table direccion 
        add constraint FK_direccion_localidad_id
        foreign key (localidad_id) 
        references localidad (id),
        add constraint FK_direccion_municipio_id
        foreign key (municipio_id) 
        references sector (id),
        add constraint FK_direccion_provincia_id
        foreign key (provincia_id) 
        references sector (id);
 
    alter table huella_de_carbono 
        add constraint FK_huella_de_carbono_organizacion_id
        foreign key (organizacion_id) 
        references organizacion (id);
 
    alter table linea 
        add constraint FK_linea_tipo_linea_id
        foreign key (tipo_linea_id) 
        references tipo_linea (id);
 
    alter table localidad 
        add constraint FK_localidad_municipio_id
        foreign key (municipio_id) 
        references sector (id);
 
    alter table organizacion 
        add constraint FK_organizacion_clasificacion_id
        foreign key (clasificacion_id) 
        references clasificacion (id),
        add constraint FK_organizacion_tipo_org_id
        foreign key (tipo_org_id) 
        references tipo_org (id),
        add constraint FK_organizacion_direccion_id
        foreign key (direccion_id) 
        references direccion (id);
 
    alter table parada 
        add constraint FK_parada_parada_id
        foreign key (linea_id)
        references linea (id);
 
    alter table sector 
        add constraint FK_sector_provincia_id
        foreign key (provincia_id) 
        references sector (id);
 
    alter table solicitud 
        add constraint FK_solicitud_area_id
        foreign key (area_id) 
        references area (id),
        add constraint FK_solicitud_org_id
        foreign key (org_id) 
        references organizacion (id),
        add constraint FK_solicitud_persona_id
        foreign key (persona_id) 
        references persona (id);
 
    alter table tramo_contratado 
        add constraint FK_tramo_contratado_direccion_fin_id
        foreign key (direccion_fin_id) 
        references direccion (id),
        add constraint FK_tramo_contratado_direccion_inicio_id
        foreign key (direccion_inicio_id) 
        references direccion (id),
        add constraint FK_tramo_contratado_propietario_id
        foreign key (propietario_id) 
        references persona (id),
        add constraint FK_tramo_contratado_tipo_servicio_id
        foreign key (tipo_servicio_id) 
        references tipo_servicio (id),
        add constraint FK_tramo_contratado_id
        foreign key (id) 
        references tramo (id);
 
    alter table tramo_limpio 
        add constraint FK_tramo_limpio_direccion_fin_id
        foreign key (direccion_fin_id) 
        references direccion (id),
        add constraint FK_tramo_limpio_direccion_inicio_id
        foreign key (direccion_inicio_id) 
        references direccion (id),
        add constraint FK_tramo_limpio_id
        foreign key (id) 
        references tramo (id);
 
    alter table tramo_particular 
        add constraint FK_tramo_particular_direccion_fin_id
        foreign key (direccion_fin_id) 
        references direccion (id),
        add constraint FK_tramo_particular_direccion_inicio_id
        foreign key (direccion_inicio_id) 
        references direccion (id),
        add constraint FK_tramo_particular_propietario_id
        foreign key (propietario_id) 
        references persona (id),
        add constraint FK_tramo_particular_tipo_combustible_id
        foreign key (tipo_combustible_id) 
        references combustible (id),
        add constraint FK_tramo_particular_tipo_particular_id
        foreign key (tipo_particular_id) 
        references tipo_particular (id),
        add constraint FK_tramo_particular_id
        foreign key (id) 
        references tramo (id);
 
    alter table tramo_publico 
        add constraint FK_tramo_publico_linea_id
        foreign key (linea_id) 
        references linea (id),
        add constraint FK_tramo_publico_parada_fin_id
        foreign key (parada_fin_id) 
        references parada (id),
        add constraint FK_tramo_publico_parada_inicio_id
        foreign key (parada_inicio_id) 
        references parada (id),
        add constraint FK_tramo_publico_id
        foreign key (id) 
        references tramo (id);
 
    alter table tramo_trayecto 
        add constraint FK_tramo_trayecto_tramo_id
        foreign key (tramo_id) 
        references tramo (id),
        add constraint FK_tramo_trayecto_trayecto_id
        foreign key (trayecto_id) 
        references trayecto (id);
 
    alter table trayecto 
        add constraint FK_trayecto_org_id
        foreign key (org_id) 
        references organizacion (id),
        add constraint FK_trayecto_persona_id
        foreign key (persona_id) 
        references persona (id);
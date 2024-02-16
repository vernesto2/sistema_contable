BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "partida" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"id_ciclo"	INTEGER NOT NULL,
	"id_tipo_partida"	INTEGER NOT NULL DEFAULT 0,
	"num_partida"	INTEGER NOT NULL,
	"comentario"	TEXT NOT NULL,
	"fecha"	TEXT NOT NULL,
	"eliminado"	INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS "cuenta" (
	"id"	INTEGER NOT NULL,
	"id_tipo_catalogo"	INTEGER NOT NULL,
	"codigo"	TEXT NOT NULL,
	"ref"	TEXT,
	"nombre"	TEXT NOT NULL,
	"nivel"	INTEGER,
	"tipo_saldo"	TEXT NOT NULL,
	"ingresos"	TEXT,
	"egresos"	TEXT,
	"eliminado"	INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY("id")
);
CREATE TABLE IF NOT EXISTS "partida_detalle" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"id_partida"	INTEGER NOT NULL,
	"id_cuenta"	INTEGER NOT NULL,
	"parcial"	NUMERIC,
	"debe"	NUMERIC,
	"haber"	NUMERIC,
	"eliminado"	INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS "usuario" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"id_persona"	INTEGER NOT NULL,
	"nombre"	TEXT NOT NULL,
	"correo"	TEXT,
	"clave"	TEXT NOT NULL,
	"resetear_clave"	INTEGER NOT NULL DEFAULT 0,
	"pregunta1"	INTEGER,
	"respuesta1"	TEXT,
	"pregunta2"	INTEGER,
	"respuesta2"	TEXT,
	"pregunta3"	INTEGER,
	"respuesta3"	TEXT,
	"salt"	TEXT,
	FOREIGN KEY("id_persona") REFERENCES "persona"("id")
);
CREATE TABLE IF NOT EXISTS "tipo_catalogo" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"tipo"	TEXT NOT NULL,
	"ref"	TEXT,
	"eliminado"	INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS "ciclo_contable" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"id_catalogo"	INTEGER NOT NULL,
	"titulo"	TEXT NOT NULL,
	"desde"	TEXT NOT NULL,
	"hasta"	TEXT,
	"eliminado"	INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS "metodo_bancaria" (
	"codigo"	INTEGER,
	"tipo"	TEXT,
	"cuenta"	TEXT,
	"tipo_catalogo"	INTEGER
);
CREATE TABLE IF NOT EXISTS "configuracion_usuario" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"id_usuario"	INTEGER NOT NULL,
	"id_ciclo_contable"	INTEGER,
	"avatar"	TEXT NOT NULL DEFAULT '/utils/avatar/avatar1.png'
);
CREATE TABLE IF NOT EXISTS "metodo_seguros" (
	"CODIGO"	INTEGER,
	"TIPO"	TEXT,
	"CONCEPTO"	TEXT,
	"tipo_catalogo"	INTEGER
);
CREATE TABLE IF NOT EXISTS "metodo_costos" (
	"Codigo"	TEXT,
	"Tipo"	TEXT,
	"Cuenta"	TEXT,
	"tipo_catalogo"	INTEGER
);
CREATE TABLE IF NOT EXISTS "metodo_sector_publico" (
	"Codigo"	INTEGER,
	"Tipo"	TEXT,
	"Concepto"	TEXT,
	"Ingresos"	TEXT,
	"Egresos"	TEXT,
	"tipo_catalogo"	INTEGER
);
CREATE TABLE IF NOT EXISTS "metodo_agricola_cultivo_cafe" (
	"Codigo"	INTEGER,
	"Tipo"	TEXT,
	"Cuenta"	TEXT,
	"tipo_catalogo"	INTEGER
);
CREATE TABLE IF NOT EXISTS "metodo_agricola_cultivo_caña_azucar" (
	"codigo"	INTEGER,
	"cuenta"	TEXT,
	"Tipo"	TEXT,
	"tipo_catalogo"	INTEGER
);
CREATE TABLE IF NOT EXISTS "metodo_perpetuo" (
	"Codigo"	INTEGER,
	"Tipo"	TEXT,
	"Cuenta"	TEXT,
	"tipo_catalogo"	INTEGER
);
CREATE TABLE IF NOT EXISTS "metodo_analitico" (
	"Codigo"	INTEGER,
	"Tipo"	TEXT,
	"Cuenta"	TEXT,
	"tipo_catalogo"	INTEGER
);
CREATE TABLE IF NOT EXISTS "persona" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"nombres"	TEXT NOT NULL,
	"apellidos"	TEXT NOT NULL,
	"tipo"	INTEGER NOT NULL DEFAULT 1,
	"carnet"	TEXT
);
COMMIT;

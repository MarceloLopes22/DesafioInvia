-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.7.0
-- PostgreSQL version: 9.3
-- Project Site: pgmodeler.com.br
-- Model Author: ---

SET check_function_bodies = false;
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: "Desafio_Invia" | type: DATABASE --
-- -- DROP DATABASE "Desafio_Invia";
-- CREATE DATABASE "Desafio_Invia"
-- ;
-- -- ddl-end --
-- 

-- object: public.usuario | type: TABLE --
-- DROP TABLE public.usuario;
CREATE TABLE public.usuario(
	cpf varchar NOT NULL,
	nome varchar,
	email varchar,
	senha varchar,
	id_cargo bigint,
	id_orgao bigint,
	CONSTRAINT usuario_pk PRIMARY KEY (cpf)

);
-- ddl-end --
-- object: public.cargo | type: TABLE --
-- DROP TABLE public.cargo;
CREATE TABLE public.cargo(
	id bigint,
	descricao varchar,
	CONSTRAINT cargo_pk PRIMARY KEY (id)

);
-- ddl-end --
-- object: public.orgao | type: TABLE --
-- DROP TABLE public.orgao;
CREATE TABLE public.orgao(
	id bigint,
	nome varchar,
	CONSTRAINT orgao_pk PRIMARY KEY (id)

);
-- ddl-end --
-- object: public.sistema | type: TABLE --
-- DROP TABLE public.sistema;
CREATE TABLE public.sistema(
	id bigint,
	nome varchar,
	CONSTRAINT sistema_pk PRIMARY KEY (id)

);
-- ddl-end --
-- object: public.telefone | type: TABLE --
-- DROP TABLE public.telefone;
CREATE TABLE public.telefone(
	id bigint,
	ddd integer,
	numero varchar,
	tipo varchar,
	cpf_usuario varchar,
	CONSTRAINT telefone_pk PRIMARY KEY (id)

);
-- ddl-end --
-- object: usuario_fk | type: CONSTRAINT --
-- ALTER TABLE public.telefone DROP CONSTRAINT usuario_fk;
ALTER TABLE public.telefone ADD CONSTRAINT usuario_fk FOREIGN KEY (cpf_usuario)
REFERENCES public.usuario (cpf) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: public.usuario_sistema | type: TABLE --
-- DROP TABLE public.usuario_sistema;
CREATE TABLE public.usuario_sistema(
	cpf_usuario varchar,
	id_sistema bigint
);
-- ddl-end --
-- object: public.cargo_seq | type: SEQUENCE --
-- DROP SEQUENCE public.cargo_seq;
CREATE SEQUENCE public.cargo_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.orgao_seq | type: SEQUENCE --
-- DROP SEQUENCE public.orgao_seq;
CREATE SEQUENCE public.orgao_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.telefone_seq | type: SEQUENCE --
-- DROP SEQUENCE public.telefone_seq;
CREATE SEQUENCE public.telefone_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.sistema_seq | type: SEQUENCE --
-- DROP SEQUENCE public.sistema_seq;
CREATE SEQUENCE public.sistema_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: cargo_fk | type: CONSTRAINT --
-- ALTER TABLE public.usuario DROP CONSTRAINT cargo_fk;
ALTER TABLE public.usuario ADD CONSTRAINT cargo_fk FOREIGN KEY (id_cargo)
REFERENCES public.cargo (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: orgao_fk | type: CONSTRAINT --
-- ALTER TABLE public.usuario DROP CONSTRAINT orgao_fk;
ALTER TABLE public.usuario ADD CONSTRAINT orgao_fk FOREIGN KEY (id_orgao)
REFERENCES public.orgao (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


-- object: usuario_fk | type: CONSTRAINT --
-- ALTER TABLE public.usuario_sistema DROP CONSTRAINT usuario_fk;
ALTER TABLE public.usuario_sistema ADD CONSTRAINT usuario_fk FOREIGN KEY (cpf_usuario)
REFERENCES public.usuario (cpf) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


-- object: sistema_fk | type: CONSTRAINT --
-- ALTER TABLE public.usuario_sistema DROP CONSTRAINT sistema_fk;
ALTER TABLE public.usuario_sistema ADD CONSTRAINT sistema_fk FOREIGN KEY (id_sistema)
REFERENCES public.sistema (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --




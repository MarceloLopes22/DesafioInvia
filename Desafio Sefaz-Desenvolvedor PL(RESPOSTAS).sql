-- Questão 1: Criar um sistema de banco de dados que gerencie 4 entidades...
CREATE DATABASE invia;

SET check_function_bodies = false;

CREATE TABLE public.usuario(
	cpf varchar NOT NULL,
	nome varchar,
	email varchar,
	senha varchar,
	id_cargo bigint,
	id_orgao bigint,
	CONSTRAINT usuario_pk PRIMARY KEY (cpf)
);

CREATE TABLE public.cargo(
	id bigint,
	descricao varchar,
	CONSTRAINT cargo_pk PRIMARY KEY (id)
);

CREATE TABLE public.orgao(
	id bigint,
	nome varchar,
	CONSTRAINT orgao_pk PRIMARY KEY (id)
);

CREATE TABLE public.sistema(
	id bigint,
	nome varchar,
	CONSTRAINT sistema_pk PRIMARY KEY (id)
);

CREATE TABLE public.telefone(
	id bigint,
	ddd integer,
	numero varchar,
	tipo varchar,
	cpf_usuario varchar,
	CONSTRAINT telefone_pk PRIMARY KEY (id)
);

ALTER TABLE public.telefone ADD CONSTRAINT usuario_fk FOREIGN KEY (cpf_usuario)
REFERENCES public.usuario (cpf) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;

CREATE TABLE public.usuario_sistema(
	cpf_usuario varchar,
	id_sistema bigint
);

CREATE SEQUENCE public.cargo_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

CREATE SEQUENCE public.orgao_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

CREATE SEQUENCE public.telefone_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

CREATE SEQUENCE public.sistema_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;

ALTER TABLE public.usuario ADD CONSTRAINT cargo_fk FOREIGN KEY (id_cargo)
REFERENCES public.cargo (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.usuario ADD CONSTRAINT orgao_fk FOREIGN KEY (id_orgao)
REFERENCES public.orgao (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.usuario_sistema ADD CONSTRAINT usuario_fk FOREIGN KEY (cpf_usuario)
REFERENCES public.usuario (cpf) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE public.usuario_sistema ADD CONSTRAINT sistema_fk FOREIGN KEY (id_sistema)
REFERENCES public.sistema (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;


-- Questao 2: Efetuar o povoamento das entidades:
INSERT INTO CARGO 
            (ID,DESCRICAO) 
     VALUES (1,'Desenvolvedor'), 
            (2,'Analista de sistemas'), 
            (3,'Analista de requisitos'), 
            (4,'Arquiteto de software'), 
            (5,'Gerente de projetos'); 

SELECT * FROM cargo;

INSERT INTO SISTEMA 
            (ID,NOME) 
     VALUES (1,'SAR'), 
            (2,'Autran'), 
            (3,'FOLHA'), 
            (4,'TAXAS'), 
            (5,'SICOR'); 

SELECT * FROM sistema;
		
INSERT INTO ORGAO 
            (ID,NOME) 
     VALUES (1,'Sefaz-PE'), 
            (2,'Sefaz-PB'), 
            (3,'Banco Central Recife'), 
            (4,'Banco Central Brasilia'), 
            (5,'Sefaz-MG'); 

SELECT * FROM ORGAO;

INSERT INTO USUARIO 
            (CPF,id_cargo,id_orgao,NOME,EMAIL,SENHA) 
     VALUES (48072289357,5,1,'Paulo Erick Araújo','pauloerick@genesyslab.com', 
             '123456'), 
            (33395141780,3,2,'Rayssa Nina Aparício','rayssanina@centrooleo.com', 
             '654321'), 
            (14806322288,2,3,'Beatriz Larissa Priscila de Paula', 
             'beatrizlarissa@escribacontabil.com','78901'), 
            (86271797199,1,4,'Calebe Fábio Henry Ferreira', 
             'calebefabio@terrabrasil.com', 
             '10987'), 
            (23814523032,4,5,'Tomás Paulo Lima','tomaspaulolima@gruporeis.net', 
             '45678'); 

SELECT * FROM USUARIO;

INSERT INTO USUARIO_SISTEMA 
            (CPF_USUARIO,ID_SISTEMA) 
     VALUES (14806322288,2), 
            (33395141780,1), 
            (23814523032,5),
			(23814523032,3); 

SELECT * FROM USUARIO_SISTEMA;

-- Questão 3: Efetuar uma extração que atenda os seguintes requisitos:

-- 1. Criar uma tabela em memória dos sistemas do usuário, que agrupe por cpf todos os sistemas do determinado usuário;
CREATE temporary TABLE TABELA_TEMPORARIA as
SELECT us.CPF_USUARIO, 
         u.NOME AS nome_usuario, 
         s.NOME AS nome_sistema 
  FROM   SISTEMA AS s
         LEFT JOIN USUARIO_SISTEMA as us 
                ON ( us.ID_SISTEMA = s.ID ) 
         JOIN USUARIO as u
           ON (u.CPF = us.CPF_USUARIO) 
  WHERE  u.CPF = '23814523032'
  GROUP  BY us.CPF_USUARIO,
  		  us.ID_SISTEMA, 
            u.nome,
            s.nome; 
-- 2. Retornar todos os usuários do sistema com o CPF (com máscara), o nome do usuário em caixa alta, bem como os cargos, 
-- orgãos e sistemas associados (se existir);			
SELECT concat(substring(us.cpf_usuario, 1, 3),'.',
       substring(us.cpf_usuario, 4, 3),'.',
       substring(us.cpf_usuario, 7, 3),'-',
	  substring(us.cpf_usuario, 10, 2)) as CPF, 
	  upper(u.nome) as nome_usuario,
       upper(c.descricao) as descricao_cargo,
       upper(o.nome) as nome_orgao,
       upper(s.nome) as nome_sistema
FROM usuario u
left join usuario_sistema us on (us.cpf_usuario = u.cpf)
inner join cargo c on (u.id_cargo = c.id)
inner join orgao o on (u.id_orgao = o.id)
inner join sistema s on (us.id_sistema = s.id)
where EXISTS(SELECT 1 FROM usuario_sistema uxs WHERE uxs.ID_SISTEMA = s.ID and uxs.cpf_usuario = u.Cpf);
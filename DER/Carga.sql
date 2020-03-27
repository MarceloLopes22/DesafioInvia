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
			
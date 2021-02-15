CREATE SEQUENCE sq_pessoa;
CREATE SEQUENCE sq_acervo;
CREATE SEQUENCE sq_usuario;

CREATE TABLE tb_usuario (
	st_login varchar(50) NOT NULL,
	st_email varchar(50) NOT NULL UNIQUE,
	st_senha varchar(200) NOT NULL,
	id_pessoa int NOT NULL
);

CREATE TABLE tb_acervo (
	id_acervo int PRIMARY KEY,
	st_assunto varchar(50) NOT NULL
);

CREATE TABLE tb_autor (
	id_pessoa int PRIMARY KEY,
	st_cpf char(11) NOT NULL UNIQUE CHECK(LENGTH(st_cpf) = 11),
	st_nome varchar(50) NOT NULL,
	st_sobrenome varchar(50),
	dt_data_nascimento date,
	
	st_nome_citacoes varchar(100),
	st_orcid varchar(20)
);

CREATE TABLE tb_usuario_comum (
	id_pessoa int PRIMARY KEY,
	st_cpf varchar(11) NOT NULL UNIQUE CHECK(LENGTH(st_cpf) = 11),
	st_nome varchar(50) NOT NULL,
	st_sobrenome varchar(50),
	dt_data_nascimento date,
	
	dt_data_cadastro date,
	id_usuario integer NOT NULL
);




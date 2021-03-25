CREATE SEQUENCE sq_item_acervo;
CREATE SEQUENCE sq_instituicao_editora;

CREATE TABLE tb_instituicao_editora (
	id_instituicao_editora int PRIMARY KEY,
	st_cnpj char(14) NOT NULL UNIQUE CHECK(LENGTH(st_cnpj) = 14),
	st_cidade varchar(20),
	st_nome varchar(50) NOT NULL
);

CREATE TABLE tb_livro (
	id_item_acervo int PRIMARY KEY,
	st_titulo varchar(50) NOT NULL,
	st_subtitulo varchar(50),
	st_area_conhecimento varchar(50) NOT NULL,
	st_codigo_catalogacao varchar(200) NOT NULL,
	dt_data_publicacao date,
	nr_situacao_item int NOT NULL,
	id_acervo int,
	id_tipo_item int NOT NULL,
	
	nr_edicao int,
	st_isbn int NOT NULL
);

CREATE TABLE tb_revista (
	id_item_acervo int PRIMARY KEY,
	st_titulo varchar(50) NOT NULL,
	st_subtitulo varchar(50),
	st_area_conhecimento varchar(50) NOT NULL,
	st_codigo_catalogacao varchar(200) NOT NULL UNIQUE,
	dt_data_publicacao date,
	nr_situacao_item int NOT NULL,
	id_acervo int,
	id_tipo_item int NOT NULL,
	
	nr_issn int,
	nr_numero int,
	nr_volume int
);

CREATE TABLE tb_trabalho_academico (
	id_item_acervo int PRIMARY KEY,
	st_titulo varchar(50) NOT NULL,
	st_subtitulo varchar(50),
	st_area_conhecimento varchar(50) NOT NULL,
	st_codigo_catalogacao varchar(200) NOT NULL,
	dt_data_publicacao date,
	nr_situacao_item int NOT NULL,
	id_acervo int,
	id_tipo_item int NOT NULL,
	
	dt_data_defesa date,
	st_nome_curso varchar(50) NOT NULL,
	nr_tipo_trabalho int NOT NULL
);


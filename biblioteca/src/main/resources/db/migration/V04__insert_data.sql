-- CRIANDO ACERVOS
INSERT INTO biblioteca.tb_acervo (id_acervo, st_assunto) values
(nextval('sq_acervo'), 'Entretenimento e Jogos'),
(nextval('sq_acervo'), 'Culinária'),
(nextval('sq_acervo'), 'Tecnologia da Informação');



-- CRIANDO ITEMS DE ACERVOS

INSERT INTO biblioteca.tb_livro
(id_item_acervo, st_titulo, st_subtitulo, st_area_conhecimento, st_codigo_catalogacao, dt_data_publicacao, nr_situacao_item, nr_edicao, st_isbn, id_acervo, id_tipo_item)
VALUES
(nextval('sq_item_acervo'), 'Star Wars', NULL, 'Ficção', 'A27TTKDH', now(), 1, 1, 111, 1,1 ),
(nextval('sq_item_acervo'), 'Harry Potter', NULL, 'Aventura', 'D49YMNHE', now(), 1, 2, 222, 1, 1),
(nextval('sq_item_acervo'), 'Senhor dos Anéis', NULL, 'Aventura', 'FFEA283F', now(), 1, 1, 333, 1, 1);

INSERT INTO biblioteca.tb_revista
(id_item_acervo, st_titulo, st_subtitulo, st_area_conhecimento, st_codigo_catalogacao, dt_data_publicacao, nr_situacao_item, nr_issn, nr_numero, nr_volume, id_acervo, id_tipo_item)
VALUES 
(nextval('sq_item_acervo'), 'Culinária: Bake Off Brasil', NULL, 'Culinária', 'JJD239LEK', now(), 1, 3, 5, 4, 2, 2),
(nextval('sq_item_acervo'), 'Culinária: Cozinha Sob Pressão', NULL, 'Culinária', 'HAH359EJ', now(), 1, 2, 7, 3, 2, 2);

INSERT INTO biblioteca.tb_trabalho_academico
(id_item_acervo, st_titulo, st_subtitulo, st_area_conhecimento, st_codigo_catalogacao, dt_data_publicacao, nr_situacao_item, dt_data_defesa, st_nome_curso, nr_tipo_trabalho, id_acervo, id_tipo_item)
VALUES
(nextval('sq_item_acervo'), 'TI Verde', NULL, 'Tecnologia da Informação', 'USH583KER', now(), 1, now(), 'DAHORA', 1, 3, 3);



-- CRIANDO AUTORES DOS ITENS

INSERT INTO biblioteca.tb_autor
(id_pessoa, st_cpf, st_nome, st_sobrenome, dt_data_nascimento, st_nome_citacoes, st_orcid)
values
(nextval('sq_pessoa'), '11122233344', 'Autor Desconhecido', NULL, NULL, '', '112233'),
(nextval('sq_pessoa'), '11122233345', 'Autor Famoso', NULL, NULL, '', '112233');



-- VINCULANDO AUTORES E ITENS

INSERT INTO biblioteca.tb_autoria
(bl_editor, id_item_acervo, id_autor)
VALUES
(false, 1, 1),
(true, 2, 1);



-- CRIANDO AS EDITORAS DOS ITENS

INSERT INTO biblioteca.tb_instituicao_editora
(id_instituicao_editora, st_cnpj, st_cidade, st_nome)
VALUES
(nextval('sq_instituicao_editora'), '12345678901234', 'São Paulo', 'Instituição Séria');



-- VINCULANDO EDITORAS E ITENS

INSERT INTO biblioteca.tb_editoras_items
(id_item_acervo, id_instituicao_editora)
VALUES
(1, 1);



-- CRIANDO USUÁRIOS COMUNS

INSERT INTO biblioteca.tb_usuario_comum
(id_pessoa, st_cpf, st_nome, st_sobrenome, dt_data_nascimento, dt_data_cadastro, id_usuario)
VALUES
(nextval('sq_pessoa'), '12345678900', 'Leonardo', 'Narita',  now(), now(), nextval('sq_usuario'));



-- CRIANDO USUÁRIOS PARA LOGIN

INSERT INTO biblioteca.tb_usuario
(st_login, st_email, st_senha, id_pessoa)
VALUES
('leo', 'leo@gmail.com', '$2a$10$gIuK43rEcR1ViykEVxd4hOTB1h8DQMeCsORSsE7uo/9yRmIiLIb.S', 3);






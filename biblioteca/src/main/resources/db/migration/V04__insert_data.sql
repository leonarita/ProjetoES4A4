-- CRIANDO ACERVOS
INSERT INTO biblioteca.tb_acervo (id_acervo, st_assunto) values
(nextval('sq_acervo'), 'Games e Jogos'),
(nextval('sq_acervo'), 'Culinária'),
(nextval('sq_acervo'), 'Tecnologia da Informação');


-- CRIANDO ITEMS DE ACERVOS

INSERT INTO biblioteca.tb_livro
(id_item_acervo, st_titulo, st_subtitulo, st_area_conhecimento, st_codigo_catalogacao, dt_data_publicacao, nr_situacao_item, nr_edicao, st_isbn)
VALUES(nextval('sq_item_acervo'), 'a', 'a', 'a', 'a', now(), 0, 0, 0);

INSERT INTO biblioteca.tb_revista
(id_item_acervo, st_titulo, st_subtitulo, st_area_conhecimento, st_codigo_catalogacao, dt_data_publicacao, nr_situacao_item, nr_issn, nr_numero, nr_volume)
VALUES(nextval('sq_item_acervo'), 'b', 'b', 'b', 'b', now(), 0, 0, 0, 0);

INSERT INTO biblioteca.tb_trabalho_academico
(id_item_acervo, st_titulo, st_subtitulo, st_area_conhecimento, st_codigo_catalogacao, dt_data_publicacao, nr_situacao_item, dt_data_defesa, st_nome_curso, nr_tipo_trabalho)
VALUES(nextval('sq_item_acervo'), 'c', 'c', 'c', 'c', now(), 0, now(), 'DAHORA', 0);



-- CRIANDO AUTORES DOS ITENS
INSERT INTO biblioteca.tb_autor
(id_pessoa, st_cpf, st_nome, st_sobrenome, dt_data_nascimento, st_nome_citacoes, st_orcid)
values
(nextval('sq_pessoa'), '11122233344', 'Autor Desconhecido', NULL, NULL, '', '112233'),
(nextval('sq_pessoa'), '11122233345', 'Autor Famoso', NULL, NULL, '', '112233');


-- VINCULANDO AUTORES E ITENS
INSERT INTO biblioteca.tb_autoria
(bl_editor, id_item_acervo, id_autor)
values
(false, 1, 1),
(true, 2, 1);


-- CRIANDO AS EDITORAS DOS ITENS
INSERT INTO biblioteca.tb_instituicao_editora
(id_instituicao_editora, st_cnpj, st_cidade, st_nome)
VALUES(0, '12345678901234', 'aqui', 'oba');


-- VINCULANDO EDITORAS E ITENS
INSERT INTO biblioteca.tb_editoras_items
(id_item_acervo, id_instituicao_editora)
values
(1, 1);


-- CRIANDO USUÁRIOS COMUNS
INSERT INTO biblioteca.tb_usuario_comum
(id_pessoa, st_cpf, st_nome, st_sobrenome, dt_data_nascimento, dt_data_cadastro, id_usuario)
VALUES(nextval('sq_pessoa'), '12345678900', 'eu', '',  now(), now(), nextval('sq_usuario'));


-- CRIANDO RESERVAS E EMPRÉSTIMOS DE ITENS AOS USUÁIOS

INSERT INTO biblioteca.tb_reserva
(dt_data_reserva, dt_data_expiracao, id_item_acervo, id_usuario_comum)
VALUES(now(), null, 1, 1);

INSERT INTO biblioteca.tb_emprestimo
(dt_data_retirada, dt_data_devoluticao_efetiva, id_item_acervo, id_usuario_comum)
VALUES(now(), null, 1, 1);








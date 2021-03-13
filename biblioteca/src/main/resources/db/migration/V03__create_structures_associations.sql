CREATE TABLE tb_autoria (
	bl_editor bool,
	id_item_acervo int,
	id_autor int,
	
	PRIMARY KEY(id_item_acervo, id_autor)
);

CREATE TABLE tb_editoras_items(
	id_item_acervo int,
	id_instituicao_editora int,
	
	PRIMARY KEY(id_item_acervo, id_instituicao_editora)
);

CREATE TABLE tb_emprestimo (
	dt_data_retirada timestamp,
	dt_data_devoluticao_efetiva date,
	id_item_acervo int,
	id_usuario_comum int,
	bl_foi_devolvido boolean default false,
	
	PRIMARY KEY(id_item_acervo, id_usuario_comum, dt_data_retirada)
);

CREATE TABLE tb_reserva (
	dt_data_reserva timestamp,
	dt_data_expiracao date,
	id_item_acervo int,
	id_usuario_comum int,
	bl_foi_retirado boolean default false,
	
	PRIMARY KEY(dt_data_reserva, id_item_acervo, id_usuario_comum)
);


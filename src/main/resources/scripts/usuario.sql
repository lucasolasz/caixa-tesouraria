INSERT INTO
	tesouraria.usuario (
		data_admissao,
		data_nascimento,
		enabled,
		id,
		-- tipo_usuario_id,
		username,
		cpf,
		first_name,
		last_name,
		num_carteira_trabalho,
		num_identidade,
		password
	)
VALUES
	(
		NULL,
		NULL,
		1,
		1,
		-- 4,
		'admin',
		NULL,
		'Administrador',
		'Sistema',
		NULL,
		NULL,
		'$2a$10$fw1BUvSZmT49jnDqgyO8NuX64/uD6ohRiAEYVm2Z3hxAyMJQTmtIi'
	);
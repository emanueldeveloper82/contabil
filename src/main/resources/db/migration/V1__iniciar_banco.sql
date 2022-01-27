-- Drop table

-- DROP TABLE contabilidade.conta;

CREATE TABLE contabilidade.conta (
	id  SERIAL PRIMARY KEY NOT NULL,
	data_cadastro date NOT NULL,
	data_pagamento date NOT NULL,
	data_vencimento date NOT NULL,
	nome varchar(255) NOT NULL,
	qtd_dias_atraso int8 NOT NULL,
	valor_corrigido numeric(19, 2) NOT NULL,
	valor_original numeric(19, 2) NOT NULL
);


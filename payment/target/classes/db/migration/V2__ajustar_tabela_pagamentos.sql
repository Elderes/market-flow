-- Remover colunas desnecessárias
ALTER TABLE pagamentos
DROP COLUMN nome,
DROP COLUMN numero,
DROP COLUMN expiracao,
DROP COLUMN codigo,
DROP COLUMN status,
DROP COLUMN forma_de_pagamento_id,
DROP COLUMN pedido_id;

-- Renomear coluna 'valor' para 'value'
ALTER TABLE pagamentos
CHANGE COLUMN valor value DECIMAL(19,2) NOT NULL;

-- Adicionar a nova coluna 'dateTime'
ALTER TABLE pagamentos
ADD COLUMN dateTime DATETIME NULL;

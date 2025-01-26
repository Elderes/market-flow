CREATE TABLE pagamentos (
	id CHAR(36) PRIMARY KEY,
    value DECIMAL(10,2) NOT NULL,
    datetime DATETIME(6) NOT NULL,
    code VARCHAR(255) NOT NULL
);
CREATE TABLE departamento (
	id_departamento BIGINT AUTO_INCREMENT,
	nm_departamento VARCHAR(50) NOT NULL,
	PRIMARY KEY(id_departamento)
);

CREATE TABLE funcionario (
	id_funcionario BIGINT AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	cpf CHAR(11) NOT NULL UNIQUE,
	email VARCHAR(100) NOT NULL UNIQUE,
	senha VARCHAR(100) NOT NULL,
	data_nascimento DATE NOT NULL,
	salario DECIMAL(10, 2),
	gerente BOOLEAN NOT NULL,
	estado CHAR(2) NOT NULL,
	cidade VARCHAR(100) NOT NULL,
	bairro VARCHAR(100) NOT NULL,
	logradouro VARCHAR(100) NOT NULL,
	numero VARCHAR(10),
	cep CHAR(9),
	id_departamento BIGINT,
	criado_por BIGINT,
	PRIMARY KEY(id_funcionario),
	FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento),
	FOREIGN KEY (criado_por) REFERENCES funcionario(id_funcionario)
);

INSERT INTO departamento (nm_departamento) VALUES ('Recursos Humanos');
INSERT INTO departamento (nm_departamento) VALUES ('Tecnologia da Informação');

INSERT INTO funcionario (nome, cpf, email, senha, data_nascimento, salario, gerente, estado, cidade, bairro, logradouro, numero, cep, id_departamento, criado_por)
VALUES ('Henrique', '095', 'henrique@email.com', 'senha', '2001-11-10', 1000, TRUE, 'SC', 'Blumenau', 'Viktor Konder', 'Rua São Paulo', '11147', '89012001', 1, NULL);

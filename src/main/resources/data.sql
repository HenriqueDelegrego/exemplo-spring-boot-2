SET @tabelas_vazias = (
    NOT EXISTS (SELECT 1 FROM departamento)
    AND NOT EXISTS (SELECT 1 FROM funcionario)
);

INSERT INTO departamento (nm_departamento)
SELECT 'Recursos Humanos'
WHERE @tabelas_vazias;

INSERT INTO departamento (nm_departamento)
SELECT 'Tecnologia da Informação'
WHERE @tabelas_vazias;

INSERT INTO funcionario (
    nome, cpf, email, senha, data_nascimento, salario,
    gerente, estado, cidade, bairro, logradouro, numero,
    cep, id_departamento, criado_por
)
SELECT
    'Henrique', '11111111111', 'henrique@email.com', 'senha',
    '2001-11-10', 1000, TRUE, 'SC', 'Blumenau',
    'Viktor Konder', 'Rua São Paulo', '11147', '89012001',
    1, NULL
WHERE @tabelas_vazias;

INSERT INTO funcionario (
    nome, cpf, email, senha, data_nascimento, salario,
    gerente, estado, cidade, bairro, logradouro, numero,
    cep, id_departamento, criado_por
)
SELECT
    'Ana', '22222222222', 'ana@email.com', 'senha',
    '1995-05-20', 2000, FALSE, 'SC', 'Florianópolis',
    'Centro', 'Rua das Flores', '2020', '88015000',
    2, 1
WHERE @tabelas_vazias;
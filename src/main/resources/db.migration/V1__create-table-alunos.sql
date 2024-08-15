CREATE TABLE alunos (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT NOT NULL,
    nota_do_primeiro_semestre DOUBLE NOT NULL,
    nota_do_segundo_semestre DOUBLE NOT NULL,
    nome_do_professor VARCHAR(255) NOT NULL,
    numero_da_sala VARCHAR(255) NOT NULL
);
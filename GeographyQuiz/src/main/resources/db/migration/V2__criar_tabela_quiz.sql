CREATE TABLE quiz (
    id INT NOT NULL auto_increment PRIMARY KEY,
    nome VARCHAR(100),
    descricao VARCHAR(300) NOT NULL,
    nota INT,
    data_hora DATETIME
);
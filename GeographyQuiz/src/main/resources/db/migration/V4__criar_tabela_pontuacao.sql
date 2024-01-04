create table pontuacao (
  id INT NOT NULL auto_increment PRIMARY KEY,
  pontuacao int not null,
  quiz_id int,
  usuario_id int,
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);

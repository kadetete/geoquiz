package br.edu.ifrn.GeographyQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.GeographyQuiz.domain.pergunta.Pergunta;;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long>{

  
}

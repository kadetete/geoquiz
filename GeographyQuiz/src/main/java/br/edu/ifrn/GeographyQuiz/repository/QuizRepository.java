package br.edu.ifrn.GeographyQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.GeographyQuiz.domain.quiz.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

  
}

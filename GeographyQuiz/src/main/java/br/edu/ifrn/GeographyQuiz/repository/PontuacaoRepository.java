package br.edu.ifrn.GeographyQuiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.GeographyQuiz.domain.pontuacao.Pontuacao;

public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long>{

  Pontuacao getReferenceByUsuarioId(Long id);
  List<Pontuacao> findAllByUsuarioId(Long id);
}

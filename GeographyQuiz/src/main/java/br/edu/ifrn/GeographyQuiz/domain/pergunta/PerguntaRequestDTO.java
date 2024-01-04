package br.edu.ifrn.GeographyQuiz.domain.pergunta;

public record PerguntaRequestDTO(String texto,
                                String resposta_correta,
                                String respostas) {
  
}

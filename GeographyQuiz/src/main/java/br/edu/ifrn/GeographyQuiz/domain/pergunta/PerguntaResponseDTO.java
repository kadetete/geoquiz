package br.edu.ifrn.GeographyQuiz.domain.pergunta;

public record PerguntaResponseDTO(Long id, 
                                String texto, 
                                String resposta_correta,
                                String respostas
                                ) {
    
    public PerguntaResponseDTO(Pergunta pergunta){
      this(pergunta.getId(), pergunta.getTexto(), pergunta.getResposta_correta(), pergunta.getRespostas());
    }
  
}

package br.edu.ifrn.GeographyQuiz.domain.pergunta;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.edu.ifrn.GeographyQuiz.domain.quiz.Quiz;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "pergunta")
@Table(name = "pergunta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O texto da pergunta obrigatório")
    private String texto;
    
    @NotNull
    private String resposta_correta;

    @NotNull
    private String respostas;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonBackReference
    private Quiz quiz;


    public Pergunta(@Valid PerguntaRequestDTO perguntaDTO) {
      this.texto = perguntaDTO.texto();
      this.resposta_correta = perguntaDTO.resposta_correta();
      this.respostas = perguntaDTO.respostas();
;
    }

}

package br.edu.ifrn.GeographyQuiz.domain.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.edu.ifrn.GeographyQuiz.domain.pergunta.*;

@Entity(name = "quiz")    //JPQL
@Table(name = "quiz")     //SQL
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O Nome do quiz obrigatório")
    private String nome;
    @NotBlank(message = "A Descriçao do quiz obrigatório")
    private String descricao;
    private int nota;
    private LocalDate data_hora;
    
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Pergunta> perguntas;
    
}

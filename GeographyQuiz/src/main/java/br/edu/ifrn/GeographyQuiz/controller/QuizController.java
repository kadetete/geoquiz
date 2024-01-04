package br.edu.ifrn.GeographyQuiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrn.GeographyQuiz.domain.quiz.Quiz;
import br.edu.ifrn.GeographyQuiz.repository.QuizRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "*")

public class QuizController {

  @Autowired
  private QuizRepository repository;

  @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Quiz quiz,
        UriComponentsBuilder uriBuilder) {
        Quiz quizLocal = repository.save(quiz);
        var uri = uriBuilder.path("/quiz/{id}").buildAndExpand(quizLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> detalhar(@PathVariable Long id) {
        Quiz quiz = repository.getReferenceById(id);
        return ResponseEntity.ok(quiz);
    }

    @GetMapping
    public ResponseEntity<Page<Quiz>> listar(@PageableDefault(size = 30, sort = { "nome" }) Pageable paginacao) {
        var quiz = repository.findAll(paginacao);
        return ResponseEntity.ok(quiz);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var quiz = repository.getReferenceById(id);
        repository.delete(quiz);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Quiz> atualizar(@RequestBody @Valid Quiz quiz) {
        Quiz quizLocal = repository.findById(
        quiz.getId()).get();

        quizLocal.setNome(quiz.getNome());
        quizLocal.setDescricao(quiz.getDescricao());
        quizLocal.setNota(quiz.getNota());
        quizLocal.setData_hora(quiz.getData_hora());

        return ResponseEntity.ok(quizLocal);
    }


  
}

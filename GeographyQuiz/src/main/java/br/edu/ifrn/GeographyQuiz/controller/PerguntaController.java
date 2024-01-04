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

import br.edu.ifrn.GeographyQuiz.domain.pergunta.*;
import br.edu.ifrn.GeographyQuiz.repository.PerguntaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pergunta")
@CrossOrigin(origins = "*")

public class PerguntaController {

  @Autowired
  private PerguntaRepository repository;

  @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Pergunta pergunta,
        UriComponentsBuilder uriBuilder) {
        Pergunta perguntaLocal = repository.save(pergunta);
        var uri = uriBuilder.path("/pergunta/{id}").buildAndExpand(perguntaLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pergunta> detalhar(@PathVariable Long id) {
        Pergunta pergunta = repository.getReferenceById(id);
        return ResponseEntity.ok(pergunta);
    }

    @GetMapping
    public ResponseEntity<Page<Pergunta>> listar(@PageableDefault(size = 30, sort = { "texto" }) Pageable paginacao) {
        var perguntas = repository.findAll(paginacao);
        return ResponseEntity.ok(perguntas);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var pergunta = repository.getReferenceById(id);
        repository.delete(pergunta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Pergunta> atualizar(@RequestBody @Valid Pergunta pergunta) {
        Pergunta perguntaLocal = repository.findById(
        pergunta.getId()).get();

        perguntaLocal.setTexto(pergunta.getTexto());
        perguntaLocal.setQuiz(pergunta.getQuiz());
        perguntaLocal.setResposta_correta(pergunta.getResposta_correta());
        perguntaLocal.setRespostas(pergunta.getRespostas());

        return ResponseEntity.ok(perguntaLocal);
    }


  
}

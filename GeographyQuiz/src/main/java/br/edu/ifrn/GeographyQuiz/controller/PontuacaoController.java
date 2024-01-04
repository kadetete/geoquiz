package br.edu.ifrn.GeographyQuiz.controller;

import java.util.List;

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

import br.edu.ifrn.GeographyQuiz.domain.pontuacao.Pontuacao;
import br.edu.ifrn.GeographyQuiz.repository.PontuacaoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pontuacao")
@CrossOrigin(origins = "*")

public class PontuacaoController {
    
  @Autowired
  private PontuacaoRepository repository;

  @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Pontuacao pontuacao,
        UriComponentsBuilder uriBuilder) {
        Pontuacao pontuacaoLocal = repository.save(pontuacao);
        var uri = uriBuilder.path("/pontuacao/{id}").buildAndExpand(pontuacaoLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pontuacao> detalhar(@PathVariable Long id) {
        Pontuacao pontuacao = repository.getReferenceById(id);
        return ResponseEntity.ok(pontuacao);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<Pontuacao>> detalharPorUsuario(@PathVariable Long id) {
        var pontuacao = repository.findAllByUsuarioId(id);
        return ResponseEntity.ok(pontuacao);
    }

    @GetMapping
    public ResponseEntity<Page<Pontuacao>> listar(@PageableDefault(size = 30, sort = { "id" }) Pageable paginacao) {
        var pontuacoes = repository.findAll(paginacao);
        return ResponseEntity.ok(pontuacoes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var pontuacao = repository.getReferenceById(id);
        repository.delete(pontuacao);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Pontuacao> atualizar(@RequestBody @Valid Pontuacao pontuacao) {
        Pontuacao pontuacaoLocal = repository.findById(
        pontuacao.getId()).get();
        
        pontuacaoLocal.setUsuario(pontuacao.getUsuario());

        return ResponseEntity.ok(pontuacaoLocal);
    }


  
}

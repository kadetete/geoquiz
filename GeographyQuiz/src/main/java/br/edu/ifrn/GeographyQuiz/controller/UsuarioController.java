package br.edu.ifrn.GeographyQuiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


import br.edu.ifrn.GeographyQuiz.domain.usuario.Usuario;
import br.edu.ifrn.GeographyQuiz.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

  @Autowired
  private UsuarioRepository repository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Usuario usuario,
        UriComponentsBuilder uriBuilder) {
        if (repository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Esse Email já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioLocal = repository.save(usuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
}

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> detalhar(@PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(@PageableDefault(size = 30, sort = { "nome" }) Pageable paginacao) {
        var usuarios = repository.findAll(paginacao);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Usuario usuario = repository.findByEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        repository.delete(usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Usuario usuario) {
        Usuario usuarioLocal = repository.findById(
        usuario.getId()).get();

        usuarioLocal.setNome(usuario.getNome());
        usuarioLocal.setEmail(usuario.getEmail());
        usuarioLocal.setSenha(usuario.getSenha());
        
        return ResponseEntity.ok(usuarioLocal);
    }


  
}

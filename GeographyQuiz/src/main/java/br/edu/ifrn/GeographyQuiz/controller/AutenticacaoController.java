package br.edu.ifrn.GeographyQuiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrn.GeographyQuiz.domain.usuario.DadosAutenticacao;
import br.edu.ifrn.GeographyQuiz.domain.usuario.Usuario;
import br.edu.ifrn.GeographyQuiz.infra.security.DadosTokenJWT;
import br.edu.ifrn.GeographyQuiz.service.TokenService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")

public class AutenticacaoController {
  
  @Autowired
  private AuthenticationManager manager;

  
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  

  @Autowired
  TokenService tokenService;

  @PostMapping
  public ResponseEntity<Object> efetuarLogin(@RequestBody DadosAutenticacao dados){
    var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
    var authentication = manager.authenticate(token);
    var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
    return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
  }

   
  @GetMapping
  public ResponseEntity<String> getSenhaBcrypt(@RequestBody String senha){
    String senhaBrypt = bCryptPasswordEncoder.encode(senha);
    return ResponseEntity.ok(senhaBrypt);
  }
}
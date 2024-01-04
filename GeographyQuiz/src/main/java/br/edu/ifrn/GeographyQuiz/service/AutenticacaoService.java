package br.edu.ifrn.GeographyQuiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifrn.GeographyQuiz.domain.usuario.Usuario;
import br.edu.ifrn.GeographyQuiz.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private UsuarioRepository repository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository.findByEmail(email);
  }

  public Usuario criarUsuario(Usuario usuario) {
    return repository.save(usuario);
  }
}
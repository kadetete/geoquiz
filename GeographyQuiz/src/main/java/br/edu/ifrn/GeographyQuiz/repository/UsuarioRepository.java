package br.edu.ifrn.GeographyQuiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifrn.GeographyQuiz.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

  UserDetails findByNome(String nome);
  Usuario findByEmail(String email);
  boolean existsByEmail(String Email);
}

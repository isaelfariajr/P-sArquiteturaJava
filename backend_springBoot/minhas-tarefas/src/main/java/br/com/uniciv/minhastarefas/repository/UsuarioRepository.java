package br.com.uniciv.minhastarefas.repository;

import br.com.uniciv.minhastarefas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNome(String username);
}

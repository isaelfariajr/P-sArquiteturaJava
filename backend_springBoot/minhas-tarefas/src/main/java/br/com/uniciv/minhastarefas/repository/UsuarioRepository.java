package br.com.uniciv.minhastarefas.repository;

import br.com.uniciv.minhastarefas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}

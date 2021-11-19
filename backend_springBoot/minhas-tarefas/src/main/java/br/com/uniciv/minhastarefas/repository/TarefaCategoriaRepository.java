package br.com.uniciv.minhastarefas.repository;

import br.com.uniciv.minhastarefas.model.TarefaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria, Integer> {
}

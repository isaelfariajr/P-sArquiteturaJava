package br.com.uniciv.minhastarefas.repository;

import br.com.uniciv.minhastarefas.model.Tarefa;
import br.com.uniciv.minhastarefas.model.TarefaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa,Integer> {

    List<Tarefa> findByDescricao(String descricao);

    List<Tarefa> findByDescricaoLike(String descricao);

    //List<Tarefa> findByCategoria(TarefaCategoria categoria);

   // @Query("select t from Tarefa t inner join t.categoria c where c.nome = ?1")
   // List<Tarefa> findByNomeCategoria(String nomeCategoria);
}

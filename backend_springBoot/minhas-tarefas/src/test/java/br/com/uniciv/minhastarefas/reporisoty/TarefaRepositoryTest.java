package br.com.uniciv.minhastarefas.reporisoty;

import br.com.uniciv.minhastarefas.model.Tarefa;
import br.com.uniciv.minhastarefas.repository.TarefaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Test
    void testFindByNomeCategoria() {

        //List<Tarefa> tarefaList = tarefaRepository.findByNomeCategoria("Estudos");
        //Assertions.assertEquals(2, tarefaList.size());
    }
}

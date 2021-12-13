package br.com.uniciv.minhastarefas.services;

import br.com.uniciv.minhastarefas.enums.TarefasStatus;
import br.com.uniciv.minhastarefas.exception.TarefaStatusException;
import br.com.uniciv.minhastarefas.model.Tarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest // Não utilizar, pois carrega todo contesto do spring
public class TarefaServiceIntegrationTest {
/*
    @Autowired
    private TarefaService tarefaService;

    @Test
    void deveIniciarTarefa() {

        Tarefa tarefa  = tarefaService.iniciarTarefaPorId(3);
        Assertions.assertEquals(TarefasStatus.EM_ANDAMENTO, tarefa.getStatus());
    }

    @Test
    void deveNaoIniciarTarefaConclida() {

        Tarefa tarefa  = tarefaService.iniciarTarefaPorId(3);
        tarefa.setStatus(TarefasStatus.CONCLUIDO);
        tarefaService.salvarTarefa(tarefa);

        Assertions.assertThrows(TarefaStatusException.class, () ->   tarefaService.iniciarTarefaPorId(3));
    }

    @Test
    void deveNaoCancelarTarefaConclida() {

        Tarefa tarefa  = tarefaService.cancelarTarefaPorId(3);
        tarefa.setStatus(TarefasStatus.CONCLUIDO);
        tarefaService.salvarTarefa(tarefa);

        Assertions.assertThrows(TarefaStatusException.class, () ->   tarefaService.iniciarTarefaPorId(3));
    }*/
}

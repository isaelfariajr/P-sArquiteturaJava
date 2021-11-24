package br.com.uniciv.minhastarefas.services;

import br.com.uniciv.minhastarefas.enums.TarefasStatus;
import br.com.uniciv.minhastarefas.exception.TarefaStatusException;
import br.com.uniciv.minhastarefas.model.Tarefa;
import br.com.uniciv.minhastarefas.repository.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    void naoDeveConcluirTarefaCancelada() {

        Integer idExemplo = 1;
        Tarefa tarefa = Tarefa.builder()
                .id(idExemplo)
                .descricao("Teste 01")
                .status(TarefasStatus.CANCELADA)
                .build();

        when(tarefaRepository.findById(idExemplo)).thenReturn(Optional.of(tarefa));

        assertThrows(TarefaStatusException.class, () -> tarefaService.concluirTarefaPorId(idExemplo));
    }

    @Test
    void naoDeveCancelarTarefaConcluida() {

        Integer idExemplo = 1;
        Tarefa tarefa = Tarefa.builder()
                .id(idExemplo)
                .descricao("Teste 01")
                .status(TarefasStatus.CONCLUIDO)
                .build();

        when(tarefaRepository.findById(idExemplo)).thenReturn(Optional.of(tarefa));

        assertThrows(TarefaStatusException.class, () -> tarefaService.cancelarTarefaPorId(idExemplo));
    }
}

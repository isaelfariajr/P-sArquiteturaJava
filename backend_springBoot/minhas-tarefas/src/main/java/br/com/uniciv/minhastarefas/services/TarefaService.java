package br.com.uniciv.minhastarefas.services;

import br.com.uniciv.minhastarefas.model.Tarefa;
import br.com.uniciv.minhastarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> getTodasTarefas() {

        return tarefaRepository.findAll();
    }

    public List<Tarefa> getTarefasPorDescricao(String descricao) {

        return tarefaRepository.findByDescricaoLike("%" + descricao + "%");
    }

    public Tarefa getTarefaPorId(Integer id) {

        return tarefaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {

        return tarefaRepository.save(tarefa);
    }

    public void deleteById(Integer id) {

        tarefaRepository.deleteById(id);
    }
}

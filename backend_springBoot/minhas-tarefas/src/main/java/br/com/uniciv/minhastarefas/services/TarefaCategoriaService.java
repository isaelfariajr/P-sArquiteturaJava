package br.com.uniciv.minhastarefas.services;

import br.com.uniciv.minhastarefas.model.TarefaCategoria;
import br.com.uniciv.minhastarefas.repository.TarefaCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TarefaCategoriaService {

    @Autowired
    private TarefaCategoriaRepository tarefaCategoriaRepository;

    public List<TarefaCategoria> getTodasTarefas() {

        return tarefaCategoriaRepository.findAll();
    }

    public TarefaCategoria getTarefaPorId(Integer id) {

        return tarefaCategoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public TarefaCategoria salvarTarefa(TarefaCategoria tarefaCategoria) {

        return tarefaCategoriaRepository.save(tarefaCategoria);
    }

    public void deleteById(Integer id) {

        tarefaCategoriaRepository.deleteById(id);
    }
}

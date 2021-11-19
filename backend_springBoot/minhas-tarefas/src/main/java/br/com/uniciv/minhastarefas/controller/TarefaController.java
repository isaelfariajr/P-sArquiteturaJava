package br.com.uniciv.minhastarefas.controller;

import br.com.uniciv.minhastarefas.model.Tarefa;
import br.com.uniciv.minhastarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping(value = "/tarefa", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Tarefa> todasAsTarefas(@RequestParam Map<String, String> parametros) {

        if (parametros.isEmpty()) {
            return tarefaService.getTodasTarefas();
        }
        String descricao = parametros.get("descricao");
        //return tarefaRepository.findByDescricao(descricao); //sem like
        return tarefaService.getTarefasPorDescricao("%" + descricao + "%");
        //return tarefaRepository.findByDescricaoLike("%" + descricao + "%"); //com like
    }

    @GetMapping(value = "/tarefa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tarefa umaTarefa(@PathVariable Integer id) {

        return tarefaService.getTarefaPorId(id);
        //return tarefaRepository.findById(id);
    }

    @PostMapping(value = "/tarefa", produces = MediaType.APPLICATION_JSON_VALUE)
    public Tarefa salvarTarefa(@Valid @RequestBody Tarefa tarefa) { //@Valid para validar os campos

        return tarefaService.salvarTarefa(tarefa);
        //return tarefaRepository.save(tarefa);
    }

    @DeleteMapping(value = "/tarefa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void excluirTarefa(@PathVariable Integer id) {

        tarefaService.deleteById(id);
       //tarefaRepository.deleteById(id);
    }
}

package br.com.uniciv.minhastarefas.controller;

import br.com.uniciv.minhastarefas.controller.request.TarefaRequest;
import br.com.uniciv.minhastarefas.controller.response.TarefaResponse;
import br.com.uniciv.minhastarefas.model.Tarefa;
import br.com.uniciv.minhastarefas.services.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(value = "/tarefa", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TarefaResponse> todasAsTarefas(@RequestParam Map<String, String> parametros) {

        List<Tarefa> tarefas = new ArrayList<>();

        if (parametros.isEmpty()) {
            tarefas = tarefaService.getTodasTarefas();
        }else {
            String descricao = parametros.get("descricao");
            tarefas = tarefaService.getTarefasPorDescricao("%" + descricao + "%");
        }

        return tarefas.stream().map(tarefa -> mapper.map(tarefa, TarefaResponse.class))
                .collect(Collectors.toList());

        //return tarefaRepository.findByDescricao(descricao); //sem like

        //return tarefaRepository.findByDescricaoLike("%" + descricao + "%"); //com like
    }

    @GetMapping(value = "/tarefa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TarefaResponse umaTarefa(@PathVariable Integer id) {

        return mapper.map(tarefaService
                .getTarefaPorId(id),TarefaResponse.class);
        //return tarefaRepository.findById(id);
    }

    @PostMapping(value = "/tarefa", produces = MediaType.APPLICATION_JSON_VALUE)
    public TarefaResponse salvarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest) { //@Valid para validar os campos

        Tarefa tarefa = mapper.map(tarefaRequest, Tarefa.class);

        return mapper.map(tarefaService
                .salvarTarefa(tarefa), TarefaResponse.class);
        //return tarefaRepository.save(tarefa);
    }

    @DeleteMapping(value = "/tarefa/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void excluirTarefa(@PathVariable Integer id) {

        tarefaService.deleteById(id);
       //tarefaRepository.deleteById(id);
    }
}

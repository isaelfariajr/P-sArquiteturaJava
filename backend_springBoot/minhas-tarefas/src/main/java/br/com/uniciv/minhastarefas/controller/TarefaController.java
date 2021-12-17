package br.com.uniciv.minhastarefas.controller;

import br.com.uniciv.minhastarefas.controller.assemble.TarefaModelAssembler;
import br.com.uniciv.minhastarefas.controller.request.TarefaRequest;
import br.com.uniciv.minhastarefas.controller.response.TarefaResponse;
import br.com.uniciv.minhastarefas.model.Tarefa;
import br.com.uniciv.minhastarefas.services.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TarefaModelAssembler tarefaModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<TarefaResponse>> todasAsTarefas(@RequestParam Map<String, String> parametros) {

        List<Tarefa> tarefas = new ArrayList<>();

        if (parametros.isEmpty()) {
            tarefas = tarefaService.getTodasTarefas();
        }else {
            String descricao = parametros.get("descricao");
            tarefas = tarefaService.getTarefasPorDescricao("%" + descricao + "%");
        }

        List<EntityModel<TarefaResponse>> tarefasModel = tarefas.stream().map(tarefaModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tarefasModel,WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(TarefaController.class)
                .todasAsTarefas(new HashMap<>())).withSelfRel());

        //return tarefaRepository.findByDescricao(descricao); //sem like

        //return tarefaRepository.findByDescricaoLike("%" + descricao + "%"); //com like
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<TarefaResponse> umaTarefa(@PathVariable Integer id) {

        return tarefaModelAssembler.toModel(tarefaService
                .getTarefaPorId(id));
        //return tarefaResponseEntityModel;
        //return tarefaRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<EntityModel<TarefaResponse>> salvarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest) { //@Valid para validar os campos

        Tarefa tarefa = mapper.map(tarefaRequest, Tarefa.class);

        /*return mapper.map(tarefaService
                .salvarTarefa(tarefa), TarefaResponse.class);
        */
        EntityModel<TarefaResponse> tarefaResponseEntityModel = tarefaModelAssembler.toModel((tarefaService
                .salvarTarefa(tarefa)));

        return ResponseEntity.created(tarefaResponseEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(tarefaResponseEntityModel);
        //return tarefaRepository.save(tarefa);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void excluirTarefa(@PathVariable Integer id) {

        tarefaService.deleteById(id);
       //tarefaRepository.deleteById(id);
    }

    @PutMapping("/{id}/iniciar")
    public EntityModel<TarefaResponse> iniciarTarefa(@PathVariable Integer id) {

        return tarefaModelAssembler.toModel(tarefaService.iniciarTarefaPorId(id));
    }

    @PutMapping("/{id}/concluir")
    public EntityModel<TarefaResponse> concluir(@PathVariable Integer id) {

        return tarefaModelAssembler.toModel(tarefaService.concluirTarefaPorId(id));
    }

    @PutMapping("/{id}/cancelar")
    public EntityModel<TarefaResponse> cancelar(@PathVariable Integer id) {

        return tarefaModelAssembler.toModel(tarefaService.cancelarTarefaPorId(id));
    }
}

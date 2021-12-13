package br.com.uniciv.minhastarefas.controller;

import br.com.uniciv.minhastarefas.controller.assemble.CategoriaModelAssembler;
import br.com.uniciv.minhastarefas.controller.request.TarefaCategoriaRequest;
import br.com.uniciv.minhastarefas.controller.response.TarefaCategoriaResponse;
import br.com.uniciv.minhastarefas.model.TarefaCategoria;
import br.com.uniciv.minhastarefas.services.TarefaCategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoriaController {

    @Autowired
    private TarefaCategoriaService tarefaCategoriaService;

    @Autowired
    private CategoriaModelAssembler categoriaModelAssembler;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/categoria")
    public CollectionModel<EntityModel<TarefaCategoriaResponse>> todasCategorias() {

        List<TarefaCategoria> categorias = tarefaCategoriaService.getTodasTarefas();

        List<EntityModel<TarefaCategoriaResponse>> categoriaModel =
                categorias.stream()
                        .map(categoriaModelAssembler::toModel)
                        .collect(Collectors.toList());

        return CollectionModel.of(categoriaModel, WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class)
                        .todasCategorias()).withSelfRel());
    /*
        return categorias.stream()
                .map(tarefaCategoria -> modelMapper.map(tarefaCategoria, TarefaCategoriaResponse.class))
                .collect(Collectors.toList());
    */
    }

    @GetMapping("/categoria/{id}")
    public EntityModel<TarefaCategoriaResponse> umaCategoria(@PathVariable Integer id) {

        return categoriaModelAssembler
                .toModel(tarefaCategoriaService.getTarefaPorId(id));
        //return modelMapper.map(tarefaCategoriaService.getTarefaPorId(id), TarefaCategoriaResponse.class);
    }

    @PostMapping("/categoria")
    public EntityModel<TarefaCategoriaResponse> salvarCategoria(
            @RequestBody TarefaCategoriaRequest tarefaCategoriaRequest) {

        return categoriaModelAssembler.toModel(
                tarefaCategoriaService.salvarTarefa(modelMapper.map(tarefaCategoriaRequest, TarefaCategoria.class)));
        //TarefaCategoria categoria = modelMapper.map(tarefaCategoriaRequest, TarefaCategoria.class);
        //return modelMapper.map(tarefaCategoriaService.salvarTarefa(categoria), TarefaCategoriaResponse.class);
    }

    @DeleteMapping("/categoria/{id}")
    public void excluir(@PathVariable Integer id) {

        tarefaCategoriaService.deleteById(id);
    }
}

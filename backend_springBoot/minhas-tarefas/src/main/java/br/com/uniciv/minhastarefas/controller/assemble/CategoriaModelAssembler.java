package br.com.uniciv.minhastarefas.controller.assemble;

import br.com.uniciv.minhastarefas.controller.CategoriaController;
import br.com.uniciv.minhastarefas.controller.response.TarefaCategoriaResponse;
import br.com.uniciv.minhastarefas.model.TarefaCategoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CategoriaModelAssembler implements
        RepresentationModelAssembler<TarefaCategoria, EntityModel<TarefaCategoriaResponse>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<TarefaCategoriaResponse> toModel(TarefaCategoria tarefaCategoria) {

        TarefaCategoriaResponse tarefaResponse = mapper.map(tarefaCategoria, TarefaCategoriaResponse.class);

        EntityModel<TarefaCategoriaResponse> tarefaCategoriaResponseResponseEntityModel = EntityModel.of(tarefaResponse,
                linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).umaCategoria(tarefaResponse.getId()))
                        .withSelfRel(),
                linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class).todasCategorias()).withRel("categorias")
                        //Link da metodo atual
                ); ////Link de outro metodo;

        return tarefaCategoriaResponseResponseEntityModel;
    }
}

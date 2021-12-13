package br.com.uniciv.minhastarefas.controller.assemble;

import br.com.uniciv.minhastarefas.controller.CategoriaController;
import br.com.uniciv.minhastarefas.controller.TarefaController;
import br.com.uniciv.minhastarefas.controller.UsuarioController;
import br.com.uniciv.minhastarefas.controller.response.TarefaResponse;
import br.com.uniciv.minhastarefas.enums.TarefasStatus;
import br.com.uniciv.minhastarefas.model.Tarefa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class TarefaModelAssembler implements
        RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {

        TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);

        EntityModel<TarefaResponse> tarefaResponseEntityModel = EntityModel.of(tarefaResponse,
                linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).umaTarefa(tarefaResponse.getId()))
                        .withSelfRel(), //Link da metodo atual
                linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).umaTarefa(tarefaResponse.getCategoriaId()))
                        .withRel("tarefas"),linkTo(WebMvcLinkBuilder.methodOn(CategoriaController.class)
                        .umaCategoria(tarefaResponse.getCategoriaId())).withRel("categoria"),
                linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).umUsuario(tarefaResponse.getUsuarioId()))
                        .withRel("usuario")); ////Link de outro metodo;

        if (TarefasStatus.EM_ANDAMENTO.equals(tarefa.getStatus())) {
            tarefaResponseEntityModel.add(
                    linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class)
                            .concluir(tarefaResponse.getId())).withRel("concluir"),
                    linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class)
                            .cancelar(tarefaResponse.getId())).withRel("cancelar")
                    );
        }

        if (TarefasStatus.ABERTO.equals(tarefa.getStatus())) {
            tarefaResponseEntityModel.add(
                    linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class)
                            .iniciarTarefa(tarefaResponse.getId())).withRel("iniciar")
            );
        }

        return tarefaResponseEntityModel;
    }
}

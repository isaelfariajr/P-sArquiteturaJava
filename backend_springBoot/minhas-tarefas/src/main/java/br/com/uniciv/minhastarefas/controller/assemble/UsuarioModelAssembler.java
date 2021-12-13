package br.com.uniciv.minhastarefas.controller.assemble;

import br.com.uniciv.minhastarefas.controller.UsuarioController;
import br.com.uniciv.minhastarefas.controller.response.UsuarioResponse;
import br.com.uniciv.minhastarefas.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class UsuarioModelAssembler implements
        RepresentationModelAssembler<Usuario, EntityModel<UsuarioResponse>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<UsuarioResponse> toModel(Usuario usuario) {

        UsuarioResponse usuarioResponse = mapper.map(usuario, UsuarioResponse.class);

        EntityModel<UsuarioResponse> UsuarioResponseEntityModel = EntityModel.of(usuarioResponse,
                linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).umUsuario(usuarioResponse.getId()))
                        .withSelfRel() //Link da metodo atual
                ); ////Link de outro metodo;

        return UsuarioResponseEntityModel;
    }
}

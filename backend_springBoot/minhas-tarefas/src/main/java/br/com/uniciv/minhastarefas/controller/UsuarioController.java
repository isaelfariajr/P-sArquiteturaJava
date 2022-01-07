package br.com.uniciv.minhastarefas.controller;

import br.com.uniciv.minhastarefas.controller.assemble.UsuarioModelAssembler;
import br.com.uniciv.minhastarefas.controller.request.UsuarioRequest;
import br.com.uniciv.minhastarefas.controller.response.UsuarioResponse;
import br.com.uniciv.minhastarefas.model.Usuario;
import br.com.uniciv.minhastarefas.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping("/usuario/{id}")
    public EntityModel<UsuarioResponse> umUsuario(@PathVariable Integer id) {

        return usuarioModelAssembler.toModel(usuarioService.getUsuarioById(id));
        //return modelMapper.map(usuarioService.getUsuarioById(id), UsuarioResponse.class);
    }

    @PostMapping
    public ResponseEntity<EntityModel<UsuarioResponse>> salvarUsuario(
            @Valid @RequestBody UsuarioRequest usuarioRequest) {

        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
        usuario = usuarioService.salvar(usuario);

        EntityModel<UsuarioResponse> usuarioModel = usuarioModelAssembler.toModel(usuario);

        return ResponseEntity
                .created(usuarioModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(usuarioModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResponse>> atualizarUsuario(
            @PathVariable Integer id , @Valid @RequestBody UsuarioRequest usuarioRequest) {

        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
        usuario = usuarioService.atualizarUsuario(id, usuario);

        EntityModel<UsuarioResponse> usuarioModel = usuarioModelAssembler.toModel(usuario);

        return ResponseEntity.ok().body(usuarioModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable Integer id) {

        usuarioService.deleteById(id);
    }
}

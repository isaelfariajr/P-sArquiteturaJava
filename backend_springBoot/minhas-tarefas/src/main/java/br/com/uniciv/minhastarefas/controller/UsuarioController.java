package br.com.uniciv.minhastarefas.controller;

import br.com.uniciv.minhastarefas.controller.assemble.UsuarioModelAssembler;
import br.com.uniciv.minhastarefas.controller.response.UsuarioResponse;
import br.com.uniciv.minhastarefas.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

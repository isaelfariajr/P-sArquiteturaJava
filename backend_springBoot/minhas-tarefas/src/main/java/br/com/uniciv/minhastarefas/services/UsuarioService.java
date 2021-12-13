package br.com.uniciv.minhastarefas.services;

import br.com.uniciv.minhastarefas.model.Usuario;
import br.com.uniciv.minhastarefas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioById(Integer id) {

        return usuarioRepository.getById(id);
    }
}

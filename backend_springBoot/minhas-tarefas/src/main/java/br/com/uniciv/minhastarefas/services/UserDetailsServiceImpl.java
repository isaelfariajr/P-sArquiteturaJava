package br.com.uniciv.minhastarefas.services;

import br.com.uniciv.minhastarefas.model.Usuario;
import br.com.uniciv.minhastarefas.repository.UsuarioRepository;
import br.com.uniciv.minhastarefas.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return UserDetailsImpl.build(usuario);
    }
}

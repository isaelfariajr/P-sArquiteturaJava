package br.com.uniciv.minhastarefas.services;

import br.com.uniciv.minhastarefas.controller.response.JwtResponse;
import br.com.uniciv.minhastarefas.model.Role;
import br.com.uniciv.minhastarefas.model.Usuario;
import br.com.uniciv.minhastarefas.repository.RoleRepository;
import br.com.uniciv.minhastarefas.repository.UsuarioRepository;
import br.com.uniciv.minhastarefas.security.JwtUtils;
import br.com.uniciv.minhastarefas.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public Usuario getUsuarioById(Integer id) {

        return usuarioRepository.getById(id);
    }

    public Usuario salvar(Usuario usuario) {

        Set<Role> roles = getRoles(usuario);
        usuario.setRoles(roles);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    private Set<Role> getRoles(Usuario usuario) {

        Set<Role> roleBanco = usuario.getRoles()
                .stream()
                .map(role -> roleRepository.findByName(role.getName())
                ).collect(Collectors.toSet());

        return roleBanco;
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuario) {

        if (!usuarioRepository.existsById(id))
            throw new EntityNotFoundException();

        usuario.setId(id);

        return salvar(usuario);

    }

    public void deleteById(Integer id) {

        usuarioRepository.deleteById(id);
    }

    public JwtResponse autenticaUsuario(String nome, String senha) {

        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(nome, senha));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwt = jwtUtils.generateJwtToken(authenticate);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authenticate.getPrincipal();

        List<String> roles = userDetailsImpl
                .getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .token(jwt)
                .id(userDetailsImpl.getId())
                .username(userDetailsImpl.getUsername())
                .roles(roles)
                .build();
    }
}

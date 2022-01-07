package br.com.uniciv.minhastarefas.security;

import br.com.uniciv.minhastarefas.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Service
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection< ? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Usuario usuario) {

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return UserDetailsImpl.builder()
                .id(usuario.getId())
                .username(usuario.getNome())
                .password(usuario.getSenha())
                .authorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    public Integer getId() {

        return id;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

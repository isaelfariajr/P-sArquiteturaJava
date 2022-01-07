package br.com.uniciv.minhastarefas.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioRequest {

    private Integer id;

    @NotBlank(message = "Campo nome do usuário não pode ser vazio")
    @Column(length = 100, unique = true)
    private String nome;

    @NotBlank(message = "Campo senha do usuário não pode ser vazio")
    @Column(nullable = false)
    private String senha;

    private Set<RoleRequest> roles;
}

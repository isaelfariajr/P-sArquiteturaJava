package br.com.uniciv.minhastarefas.controller.response;

import br.com.uniciv.minhastarefas.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private List<String> roles;

    public boolean isAdmin() {

        return roles.contains(ERole.ROLE_ADMIN.name());
    }
}

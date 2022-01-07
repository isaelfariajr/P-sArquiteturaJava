package br.com.uniciv.minhastarefas.config;

import br.com.uniciv.minhastarefas.enums.ERole;
import br.com.uniciv.minhastarefas.enums.TarefasStatus;
import br.com.uniciv.minhastarefas.model.Role;
import br.com.uniciv.minhastarefas.model.Tarefa;
import br.com.uniciv.minhastarefas.model.TarefaCategoria;
import br.com.uniciv.minhastarefas.model.Usuario;
import br.com.uniciv.minhastarefas.repository.RoleRepository;
import br.com.uniciv.minhastarefas.repository.TarefaCategoriaRepository;
import br.com.uniciv.minhastarefas.repository.TarefaRepository;
import br.com.uniciv.minhastarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;

@Configuration
@Profile("dev") //Será utilizado em contexto de dev.
public class CarregaBaseDados {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TarefaCategoriaRepository tarefaCategoriaRepository;
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Bean // Toda vez que a aplicação executar , esse metodo será executado
    CommandLineRunner executar() {

        return args -> {

            //Criando A role
            Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            roleAdmin = roleRepository.save(roleAdmin);

            Usuario usuario = Usuario.builder()
                    .nome("Admin")
                    .senha(encoder.encode("123456"))
                    .roles(Set.of(roleAdmin))
                    .build();

            usuario = usuarioRepository.saveAndFlush(usuario);

            TarefaCategoria categoria = TarefaCategoria.builder()
                    .nome("Estudos")
                    .build();

            categoria = tarefaCategoriaRepository.saveAndFlush(categoria);

            Tarefa tarefa = Tarefa.builder()
                    .descricao("Aprender Spring Boot")
                    .dataEntrega(LocalDate.now().plusDays(1))
                    .status(TarefasStatus.ABERTO)
                    .visivel(true)
                    .tarefaCategoria(categoria)
                    .usuario(usuario)
                    .build();

            tarefaRepository.save(tarefa);
        };
    }
}

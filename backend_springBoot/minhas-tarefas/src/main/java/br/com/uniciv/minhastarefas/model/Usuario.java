package br.com.uniciv.minhastarefas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private String senha;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY )
    private List<Tarefa> tarefas;
    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "usuarios_roles",
            joinColumns =  @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}

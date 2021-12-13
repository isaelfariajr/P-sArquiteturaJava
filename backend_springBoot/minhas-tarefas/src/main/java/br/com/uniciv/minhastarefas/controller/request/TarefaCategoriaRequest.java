package br.com.uniciv.minhastarefas.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "tarefas_categoria")
public class TarefaCategoriaRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Campo descrição não pode estar vazio") // não permitir nulo
    @Size(min = 5, max = 50, message = "Campo no deve ter entre 1 e 50 letras") //Trata os tamanho do campo
    private String nome;
}

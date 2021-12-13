package br.com.uniciv.minhastarefas.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarefaCategoriaResponse {

    private Integer id;
    private String nome;
}

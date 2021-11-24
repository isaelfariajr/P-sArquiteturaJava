package br.com.uniciv.minhastarefas.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TarefaResponse {

    private Integer id;
    private String descricao;
    private String status;
    private LocalDate dataEntrega;
    private Integer categoriaId;
    private Integer usuarioId;
}

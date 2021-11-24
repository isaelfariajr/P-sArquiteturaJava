package br.com.uniciv.minhastarefas.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TarefaRequest {

    private Integer id;
    @NotBlank(message = "{tarefa.decricao.not-blank}") // não permitir nulo
    @Size(min = 5, max = 150, message = "{tarefa.descricao.size}") //Trata os tamanho do campo
    private String descricao;
    @FutureOrPresent(message = "{tarefa.descricao.future-or-present}")
    private LocalDate dataEntrega;
    @NotNull(message = "{tarefa.categoria.not-null}") // não permitir nulo
    @Min(value = 1, message = "{tarefa.categoria.min}") //Trata para que tenha um valor
    private Integer categoriaId;
    @NotNull(message = "{tarefa.usuario.not-null}") // não permitir nulo
    @Min(value = 1, message = "{tarefa.usuario.min}") //Trata para que tenha um val
    private Integer usuarioId;
}

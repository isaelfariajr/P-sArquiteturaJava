package br.com.uniciv.minhastarefas.model;

import br.com.uniciv.minhastarefas.enums.TarefasStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "{tarefa.decricao.not-blank}") // não permitir nulo
	@Size(min = 5, max = 150, message = "{tarefa.descricao.size}") //Trata os tamanho do campo
	@Column(name = "ds_tarefa", nullable = false, length = 150)
	private String descricao;
    @Enumerated(EnumType.STRING)
	private TarefasStatus status = TarefasStatus.ABERTO;
    @FutureOrPresent(message = "{tarefa.descricao.future-or-present}") // Passa entre chave a msg que esta no arquivo message.propeties
	private LocalDate dataEntrega;
	private boolean visivel;
	@ManyToOne
	@JoinColumn(nullable = false)
	private TarefaCategoria tarefaCategoria;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;
}

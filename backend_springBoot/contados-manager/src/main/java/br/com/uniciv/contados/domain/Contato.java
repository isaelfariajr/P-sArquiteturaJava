package br.com.uniciv.contados.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contato {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private String nome;
	private String email;
	private String telefone;
}

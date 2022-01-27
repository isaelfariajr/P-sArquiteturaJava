package br.com.uniciv.rest.livraria;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
@XmlRootElement(name="livro") // Notação para definir que gera xml da classe livro
@XmlAccessorType(XmlAccessType.FIELD) //Notação para dizer que tem campos
public class Livro {

	@XmlAttribute // Notação para tranformar o atribito em um elemento do xml
	private Long id;	
	@XmlElement //Define que será um elemento do xml
	private String titulo;
	@XmlElement
	private String isbn;
	@XmlElement
	private String genero;
	@XmlElement
	private Double preco;
	@XmlElement
	private List<Autor> autor = new ArrayList<Autor>();
	
	public Livro(Long id, String titulo, String isbn, String genero, Double preco,
			String...nomes) {
		
		super();
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.genero = genero;
		this.preco = preco;
		
		for(String nome: nomes) {
			autor.add(Autor.builder()
					.id(Math.round( Math.random() * 10))
					.nome(nome)
					.build());
		}
	}
}

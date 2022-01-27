package br.com.uniciv.rest.livraria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement(name="autor") // Notação para definir que gera xml da classe livro
@XmlAccessorType(XmlAccessType.FIELD) //Notação para dizer que tem campos
public class Autor {

	@XmlAttribute // Notação para tranformar o atribito em um elemento do xml
	private Long id;
	@XmlElement
	private String nome;
}

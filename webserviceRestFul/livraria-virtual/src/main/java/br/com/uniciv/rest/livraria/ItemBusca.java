package br.com.uniciv.rest.livraria;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name="itemBusca") 
@XmlAccessorType(XmlAccessType.FIELD) 
public class ItemBusca {

	@XmlElement
	private Livro livro;
	@XmlElement
	@XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	private List<Link> links = new ArrayList<>();
	
	public void addLink(Link link) {
		this.links.add(link);
	}
	
}

package br.com.uniciv.rest.livraria;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.process.internal.RequestScoped;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("livro")
public class LivroResource {
	
	private LivroRepositorio livroRepositorio = LivroRepositorio.getInstance();

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Livros getLivros() {
		
		Livros livros = new Livros(); 
		livros.setLivros(livroRepositorio.getLivros()); 
		return livros;
	}
	
	@GET
	@Path("/{isbn}")
	public ItemBusca getLivroPorIsbn(@PathParam("isbn") String isbn) {
		
		try {
			Livro livro = livroRepositorio.getLivroPorIsbn(isbn);
			ItemBusca item = new ItemBusca();
			item.setLivro(livro);
			//Criando links
			Link link = Link.fromUri("/carrinho/" + livro.getId())
					.rel("carinho")
					.type("POST")
					.build();
			item.addLink(link);
			return item;
		}catch(LivroNaoEmcontradoException e ) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}	
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response criaLivro(Livro livro) {
		try {
			livroRepositorio.adicionarLivro(livro);
		}catch(LivroExistenteException e) {
			//Retornar statusCode 409 - Pois, o item já existe
			throw new WebApplicationException(Status.CONFLICT);
		}	
		//Monta uri para 201
		URI uriLocation = UriBuilder.fromPath("livro/{isbn}")
				.build(livro.getIsbn());
		
		return Response.created(uriLocation).entity(livro).build();
	}
	
	@PUT
	@Path("/{isbn}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response atualizaLivro(@PathParam("isbn") String isbn, Livro livro) {
		try {
			Livro livroDoEstoque = livroRepositorio.getLivroPorIsbn(isbn);
			livroDoEstoque.setAutor(livro.getAutor());
			livroDoEstoque.setGenero(livro.getGenero());
			livroDoEstoque.setPreco(livro.getPreco());
			livroDoEstoque.setTitulo(livro.getTitulo());
			
			livroRepositorio.atualizaLivro(livroDoEstoque);			
		}catch(LivroExistenteException e) {
			//Retornar statusCode 
			throw new WebApplicationException(Status.NOT_FOUND);
		}	
		//devolve codigo 200
		return Response.ok().entity(livro)
				.build();
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void removeLivro(@PathParam("id") Long id) {
		try {
			livroRepositorio.removeLivro(id);			
		}catch(LivroExistenteException e) {
			//Retornar statusCode 
			throw new WebApplicationException(Status.NOT_FOUND);
		}	
	}
}

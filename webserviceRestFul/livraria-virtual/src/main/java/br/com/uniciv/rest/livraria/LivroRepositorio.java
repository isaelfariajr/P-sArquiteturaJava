package br.com.uniciv.rest.livraria;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class LivroRepositorio {
	
	private Map<Long, Livro> livros = new Hashtable<>();
	
	private static LivroRepositorio repo;
	
	public static LivroRepositorio getInstance() {
		
		if (repo == null) {
			repo = new LivroRepositorio();
		}
		return repo;
	}
	
	private LivroRepositorio() {
		
		Livro livro1 = new Livro(1L, "Livro A", "ISBN-1234", "Genero A", 23.99, "Autor A");
		Livro livro2 = new Livro(2L, "Livro B", "ISBN-4321", "Genero N", 19.99, "Autor B");
		
		livros.put(livro1.getId(), livro1);
		livros.put(livro2.getId(), livro2);
	}
	
	public List<Livro> getLivros() {
		
		return new ArrayList<>(livros.values());// pega os elementos do map e manda para a lista
	}

	public Livro getLivroPorIsbn(String isbn) {

		for(Livro livro : livros.values()) {
			if (isbn.equals(livro.getIsbn())) {
				return livro;
			}
		}
		throw new LivroNaoEmcontradoException();
	}
	
	public void adicionarLivro(Livro livro) {
		
		if (livros.containsKey(livro.getId())) {
			throw new LivroExistenteException();
		}
		livros.put(livro.getId(), livro);
	}
	
	public void atualizaLivro(Livro livro ) {
		
		if (livros.containsKey(livro.getId())) {
			throw new LivroNaoEmcontradoException();
		}
		livros.put(livro.getId(), livro);
	}
	
	public void removeLivro(Long id) {
		
		if (livros.containsKey(id)) {
			throw new LivroNaoEmcontradoException();
		}
		livros.remove(id);
		
	}
}

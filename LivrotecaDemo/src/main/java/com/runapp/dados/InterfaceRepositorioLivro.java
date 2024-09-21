package com.runapp.dados;

import java.util.List;

import com.runapp.negocio.basica.livros.Livro;

public interface InterfaceRepositorioLivro {

	Livro findById(Long idLivro);

	void save(Livro livro);

	List<Livro> findAll();

	Livro findByTitulo(String titulo);

	List<Livro> findByAutor(String autor);

	List<Livro> findByGenero(String genero);
}
package com.runapp.dados;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.runapp.negocio.basica.livros.Livro;

/**
 * @author Letícia Baracho
 * @version 1.0
 */
@Repository
public interface InterfaceRepositorioLivro {

	Livro findById(Long idLivro);

	void save(Livro livro);

	List<Livro> findAll();

	Livro findByTitulo(String titulo);

	List<Livro> findByAutor(String autor);

	List<Livro> findByGenero(String genero);

	List<Livro> findByTituloAndAutor(String titulo, String autor);
}
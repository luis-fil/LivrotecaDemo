package com.runapp.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runapp.negocio.basica.livros.Livro;

/**
 * @author Letícia Baracho
 * @version 1.0
 */
@Repository
public interface InterfaceRepositorioLivro extends JpaRepository<Livro, Long> {

	Livro findByTitulo(String titulo);

	List<Livro> findByAutor(String autor);

	List<Livro> findByGenero(String genero);

	List<Livro> findByTituloAndAutor(String titulo, String autor);
}
package com.runapp.dados;

import java.util.List;

import com.runapp.negocio.basica.livros.Livro;

public interface InterfaceRepositorioLivro {

	Livro findById(Long idLivro);

	void save(Livro livro);

	List<Livro> findAll();
}
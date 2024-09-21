package com.runapp.dados;

import com.runapp.negocio.basica.livros.Livro;

public interface InterfaceRepositorioLivro {

	Livro findById(Long idLivro);

	void save(Livro livro);

}
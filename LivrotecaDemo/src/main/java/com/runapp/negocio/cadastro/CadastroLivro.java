package com.runapp.negocio.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioLivro;
import com.runapp.negocio.basica.livros.*;

@Service
public class CadastroLivro {
	@Autowired
	private InterfaceRepositorioLivro colecaoLivros;
	
	public void aumentarQuantidade(Long idLivro, int quantidade) {
		Livro livro = colecaoLivros.findById(idLivro);
	    livro.setQuantidade(livro.getQuantidade() + quantidade);
	    colecaoLivros.save(livro);
	}

	public void diminuirQuantidade(Long idLivro, int quantidade) {
		Livro livro = colecaoLivros.findById(idLivro);
	    if (livro.getQuantidade() < quantidade) {
	    	//exception
	    }
	    livro.setQuantidade(livro.getQuantidade() - quantidade);
	    colecaoLivros.save(livro);
	}
}



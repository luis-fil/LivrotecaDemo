package com.runapp.negocio.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioLivro;
import com.runapp.dados.InterfaceRepositorioAvaliacao;
import com.runapp.negocio.basica.livros.*;
import com.runapp.negocio.basica.usuarios.Cliente;

@Service
public class CadastroLivro {
	@Autowired
	private InterfaceRepositorioLivro colecaoLivros;
	@Autowired
    private InterfaceRepositorioAvaliacao colecaoAvaliacoes;
	
	public void adicionarLivro(String titulo, String autor, double valorLivroFisico, double valorEbook, 
            int numeroPaginas, String genero, String sinopse, String editora, int quantidade) {
		Livro novoLivro = new Livro(titulo, autor, valorLivroFisico, valorEbook, numeroPaginas, genero, sinopse, editora, quantidade);
		colecaoLivros.save(novoLivro);
	}
	
	public void atualizarLivro(Long idLivro, String novoTitulo, String novoAutor, double novoValorLivroFisico, double novoValorEbook, 
            int novoNumeroPaginas, String novoGenero, String novaSinopse, String novaEditora, int novaQuantidade) {
		Livro livro = colecaoLivros.findById(idLivro);
		if(livro != null) {
			livro.setTitulo(novoTitulo);
			livro.setAutor(novoAutor);
			livro.setValorLivroFisico(novoValorLivroFisico);
			livro.setValorEbook(novoValorEbook);
			livro.setNumeroPaginas(novoNumeroPaginas);
			livro.setGenero(novoGenero);
			livro.setSinopse(novaSinopse);
			livro.setEditora(novaEditora);
			livro.setQuantidade(novaQuantidade);
		}
		else {
			//exception
		}
	}
	
	public void avaliarLivro(Long idLivro, Cliente cliente, String titulo, String corpo, double nota) {
        Livro livro = colecaoLivros.findById(idLivro);
        if(livro != null) {
        	Avaliacao avaliacao = new Avaliacao(titulo, corpo, nota, livro, cliente);
        	colecaoAvaliacoes.save(avaliacao);
        	livro.getAvaliacoes().add(avaliacao);
        	atualizarNotaLivro(livro);
        	colecaoLivros.save(livro);
        }
        else {
        	//except
        }
    }

    private void atualizarNotaLivro(Livro livro) {
    	double somaNotas = 0.0;
    	double mediaNotas;
        int totalAvaliacoes = livro.getAvaliacoes().size();
        for (Avaliacao avaliacao : livro.getAvaliacoes()) {
            somaNotas += avaliacao.getNota();
        }
        if (totalAvaliacoes > 0) {
            mediaNotas = somaNotas / totalAvaliacoes;
        } else {
            mediaNotas = 0.0;
        } 
        livro.setNota(mediaNotas);
    }
	
	public void aumentarQuantidade(Long idLivro, int quantidade) {
		Livro livro = colecaoLivros.findById(idLivro);
		if(livro != null) {
			livro.setQuantidade(livro.getQuantidade() + quantidade);
			colecaoLivros.save(livro);
		}
		else {
			//exception
		}
	}

	public void diminuirQuantidade(Long idLivro, int quantidade) {
		Livro livro = colecaoLivros.findById(idLivro);
		if(livro != null) {
			if (livro.getQuantidade() < quantidade) {
	    	//exception
			}
			livro.setQuantidade(livro.getQuantidade() - quantidade);
			colecaoLivros.save(livro);
		}
		else {
			//exception
		}
	}
}
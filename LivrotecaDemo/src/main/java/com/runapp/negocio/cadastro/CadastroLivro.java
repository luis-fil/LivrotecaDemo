package com.runapp.negocio.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioLivro;
import com.runapp.negocio.basica.livros.*;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.cadastro.exception.AvaliacaoNaoExisteException;
import com.runapp.negocio.cadastro.exception.LivroJaExisteException;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;
import com.runapp.negocio.cadastro.exception.QuantidadeInsuficienteException;

/**
 * Essa classe cria os métodos necessarios para manipulação dos livros
 * @author Letícia Baracho
 * @version 1.0
 */

@Service
public class CadastroLivro implements InterfaceCadastroLivro {
	@Autowired
	private InterfaceRepositorioLivro colecaoLivros;
	
	@Override
	public void adicionarLivro(String titulo, String autor, double valorLivroFisico, double valorEbook, 
	        int numeroPaginas, String genero, String sinopse, String editora, int quantidade) 
	        throws LivroJaExisteException {
	    List<Livro> livrosExistentes = colecaoLivros.findByTituloAndAutor(titulo, autor);
	    if (!livrosExistentes.isEmpty()) {
	        throw new LivroJaExisteException(titulo, autor);
	    }
	    else {
	    	Livro novoLivro = new Livro(titulo, autor, valorLivroFisico, valorEbook, numeroPaginas, genero, sinopse, editora, quantidade);
	    	colecaoLivros.save(novoLivro);
	    }
	}
	@Override
	public void atualizarLivro(Long idLivro, String novoTitulo, String novoAutor, double novoValorLivroFisico, double novoValorEbook, 
            int novoNumeroPaginas, String novoGenero, String novaSinopse, String novaEditora, int novaQuantidade) 
            		throws LivroNaoExisteException {
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
			throw new LivroNaoExisteException(idLivro);
		}
	}
	
	@Override
	public void avaliarLivro(Long idLivro, Cliente cliente, String titulo, String corpo, double nota)
			throws LivroNaoExisteException {
        Livro livro = colecaoLivros.findById(idLivro);
        if(livro != null) {
        	Avaliacao avaliacao = new Avaliacao(titulo, corpo, nota, livro, cliente);
        	livro.getAvaliacoes().add(avaliacao);
        	atualizarNotaLivro(livro);
        	colecaoLivros.save(livro);
        }
        else {
        	throw new LivroNaoExisteException(idLivro);
        }
    }
	
	@Override
	public void removerAvaliacao(Long idLivro, Long idAvaliacao)
	        throws LivroNaoExisteException, AvaliacaoNaoExisteException {
	    Livro livro = colecaoLivros.findById(idLivro);
	    if (livro != null) {
	        Avaliacao avaliacaoARemover = null;
	        for (Avaliacao avaliacao : livro.getAvaliacoes()) {
	            if (avaliacao.getId() == idAvaliacao) {
	                avaliacaoARemover = avaliacao;
	                break;
	            }
	        }
	        if (avaliacaoARemover == null) {
	            throw new AvaliacaoNaoExisteException(idAvaliacao);
	        }
	        livro.getAvaliacoes().remove(avaliacaoARemover);
	        atualizarNotaLivro(livro);
	        colecaoLivros.save(livro);
	    } else {
	        throw new LivroNaoExisteException(idLivro);
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
	
	@Override
	public void aumentarQuantidade(Long idLivro, int quantidade)
			throws LivroNaoExisteException {
		Livro livro = colecaoLivros.findById(idLivro);
		if(livro != null) {
			livro.setQuantidade(livro.getQuantidade() + quantidade);
			colecaoLivros.save(livro);
		}
		else {
			throw new LivroNaoExisteException(idLivro);
		}
	}

	@Override
	public void diminuirQuantidade(Long idLivro, int quantidade)
			throws LivroNaoExisteException, QuantidadeInsuficienteException {
		Livro livro = colecaoLivros.findById(idLivro);
		if(livro != null) {
			if (livro.getQuantidade() < quantidade) {
				throw new QuantidadeInsuficienteException(quantidade, livro.getQuantidade());
			}
			livro.setQuantidade(livro.getQuantidade() - quantidade);
			colecaoLivros.save(livro);
		}
		else {
			throw new LivroNaoExisteException(idLivro);
		}
	}
	
	@Override
	public List<Livro> listarLivros() {
		return colecaoLivros.findAll();
	}
	
	@Override
	public Livro procurarLivroId(Long id)
			throws LivroNaoExisteException{
		Livro livro = colecaoLivros.findById(id);
		if(livro != null) {
			return livro;
		}
		else {
			throw new LivroNaoExisteException(id);
		}
	}
	
	@Override
	public Livro procurarLivroTitulo(String titulo)
			throws LivroNaoExisteException{
		Livro livro = colecaoLivros.findByTitulo(titulo);
		if(livro != null) {
			return livro;
		}
		else {
			throw new LivroNaoExisteException(titulo);
		}
	}
	
	@Override
    public List<Livro> procurarLivroAutor(String autor)
    		throws LivroNaoExisteException {
        List<Livro> livros = colecaoLivros.findByAutor(autor);
        if (livros != null && !livros.isEmpty()) {
            return livros;
        }
        else {
            throw new LivroNaoExisteException(autor);
        }
    }

	@Override
    public List<Livro> procurarLivroGenero(String genero)
    		throws LivroNaoExisteException {
        List<Livro> livros = colecaoLivros.findByGenero(genero);
        if (livros != null && !livros.isEmpty()) {
            return livros;
        }
        else {
            throw new LivroNaoExisteException(genero);
        }
    }
}

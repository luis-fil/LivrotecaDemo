package com.runapp.negocio.cadastro.exception;

/**
 * Essa é uma classe de exceção para quando um livro não for encontrado no sistema
 * @author Letícia Baracho
 * @version 1.0
 */

public class LivroNaoExisteException extends Exception {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String criterio;
		
	public LivroNaoExisteException(Long id) {
		super("Não existe no sistema um livro com o id:" + id);
		this.id = id;
	}
	
	public LivroNaoExisteException(String criterio) {
		super("Não existe no sistema um livro com o criterio:" + criterio);
		this.criterio = criterio;
	}

	public Long getId() {
		return id;
	}
	
	public String getCriterio() {
		return criterio;
	}
}
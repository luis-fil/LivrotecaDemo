package com.runapp.negocio.cadastro.exception;


/**
 * Essa é uma classe de exceção para quando uma avaliação não for encontrada no sistema
 * @author Letícia Baracho
 * @version 1.0
 */
public class AvaliacaoNaoExisteException extends Exception {
	private static final long serialVersionUID = 1L;
	private Long id;
		
	public AvaliacaoNaoExisteException(Long id) {
		super("Não existe no sistema um usuário com o id:" + id);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}

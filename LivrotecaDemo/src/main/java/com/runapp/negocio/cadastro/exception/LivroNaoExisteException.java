package com.runapp.negocio.cadastro.exception;

public class LivroNaoExisteException extends Exception {
	private static final long serialVersionUID = 1L;
	private Long id;
		
	public LivroNaoExisteException(Long id) {
		super("Não existe no sistema um usuário com o id informado");
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
}
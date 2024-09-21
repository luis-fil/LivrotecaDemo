package com.runapp.negocio.cadastro.exception;

public class LivroNaoExisteException extends Exception {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String criterio;
		
	public LivroNaoExisteException(Long id) {
		super("Não existe no sistema um usuário com o id:" + id);
		this.id = id;
	}
	
	public LivroNaoExisteException(String criterio) {
		super("Não existe no sistema um usuário com o criterio:" + criterio);
		this.criterio = criterio;
	}


	public Long getId() {
		return id;
	}
	
	public String getCriterio() {
		return criterio;
	}
}
package com.runapp.negocio.cadastro.exception;

public class UsuarioNaoExisteException extends Exception {
	private static final long serialVersionUID = 3314225175784890822L;
	private String email;
	private Long id;
	
	public UsuarioNaoExisteException(String email) {
		super("Não existe no sistema um usuário com o email informado");
		this.email = email;
	}
	
	public UsuarioNaoExisteException(Long id) {
		super("Não existe no sistema um usuário com o email informado");
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}
}

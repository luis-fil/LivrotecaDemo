package com.runapp.negocio.cadastro.exception;

public class UsuarioNaoExisteException extends Exception {
	private static final long serialVersionUID = 3314225175784890822L;
	private String email;
	
	public UsuarioNaoExisteException(String email) {
		super("Não existe no sistema um usuário com o email informado");
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}

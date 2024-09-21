package com.runapp.negocio.cadastro.exception;

public class UsuarioDuplicadoException extends Exception {
	private static final long serialVersionUID = 3119672336642991925L;
	private String email;
	
	public UsuarioDuplicadoException(String email) {
		super("Não é possível cadastrar dois usuários com o mesmo email");
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
}

package com.runapp.negocio.fachada.exception;

public class NaoEhClienteException extends Exception {
	private static final long serialVersionUID = -8543056549226102393L;
	private String email;
	
	public NaoEhClienteException(String email) {
		super("Não existe um cliente com o email informado no sistema e apenas clientes podem utilizar essa funcionalidade");
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}

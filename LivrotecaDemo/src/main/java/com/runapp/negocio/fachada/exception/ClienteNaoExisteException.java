package com.runapp.negocio.fachada.exception;

public class ClienteNaoExisteException extends Exception {
	private static final long serialVersionUID = -8543056549226102393L;
	private Long id;
	
	public ClienteNaoExisteException(Long id) {
		super("Não existe um cliente com o id informado no sistema e apenas clientes podem utilizar essa funcionalidade");
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}

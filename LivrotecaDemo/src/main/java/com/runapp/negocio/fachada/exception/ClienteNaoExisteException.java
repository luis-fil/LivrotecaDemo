package com.runapp.negocio.fachada.exception;

/**
 * Essa é uma classe de exceção para quando um cliente não existir no sistema
 * @author José Matheus
 * @version 1.0
 */
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

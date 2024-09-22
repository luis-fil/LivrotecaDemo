package com.runapp.negocio.fachada.exception;

public class QuantidadeInvalidaException extends Exception {
	private static final long serialVersionUID = 8191159875585401439L;
	private int quantidade;
	
	public QuantidadeInvalidaException(int quantidade) {
		super("A quantidade informada é inválida!");
		this.quantidade = quantidade;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
}

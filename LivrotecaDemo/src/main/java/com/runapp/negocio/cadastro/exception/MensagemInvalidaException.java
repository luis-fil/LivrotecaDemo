package com.runapp.negocio.cadastro.exception;

public class MensagemInvalidaException extends Exception {
	private static final long serialVersionUID = 5388704074965457623L;
	
	public MensagemInvalidaException() {
		super("A Mensagem inserida é inválida");
	}
}

package com.runapp.negocio.fachada.exception;

public class MensagemVazioException extends Exception {
	private static final long serialVersionUID = -5750625883030635380L;
	public MensagemVazioException() {
		super("Nao ha mensagens nesse topico");
	}
}

package com.runapp.negocio.fachada.exception;

public class UsuarioInvalidoException extends Exception {
	private static final long serialVersionUID = -4996419729821301980L;

	public UsuarioInvalidoException() {
		super("O usuario remetente é inválido");
	}
}

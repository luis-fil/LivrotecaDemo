package com.runapp.negocio.cadastro.exception;
/**
 * @author Luis Filipe
 * @version 1.02
 */
public class TopicoInvalidoException extends Exception {
	private static final long serialVersionUID = 6673185549112480270L;
	
	public TopicoInvalidoException() {
		super("Topico inserido inválido");
	}
}

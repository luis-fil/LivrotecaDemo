package com.runapp.negocio.cadastro.exception;

public class TipoDiferenteUsuarioException extends Exception {
	private static final long serialVersionUID = -3559296284004531576L;
	
	public TipoDiferenteUsuarioException(String mensagem) {
		super(mensagem);
	}
}

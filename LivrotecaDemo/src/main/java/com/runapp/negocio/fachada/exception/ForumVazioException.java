package com.runapp.negocio.fachada.exception;

public class ForumVazioException extends Exception {
	private static final long serialVersionUID = -7153060889192413291L;

	public ForumVazioException() {
		super("Nao ha foruns cadastrados");
	}
}

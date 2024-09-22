package com.runapp.negocio.cadastro.exception;

import com.runapp.negocio.basica.foruns.Topico;
/**
 * @author Luis Filipe
 * @version 1.02
 */
public class ForumInvalidoException extends Exception {
	private static final long serialVersionUID = -1616891515775298026L;
	
	public ForumInvalidoException() {
		super("O forum informado não é válido");
	}
	
	public ForumInvalidoException(Topico t) {
		super("O Topico não está vinculado a um forum válido");
	}
}

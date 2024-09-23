package com.runapp.negocio.cadastro.exception;

/**
 * Essa é uma classe de exceção para caso o usuário passado para modificar um cliente 
 * ou administrador não seja do mesmo tipo desse usuário a ser modificado
 * @author José Matheus
 * @version 1.0
 */
public class TipoDiferenteUsuarioException extends Exception {
	private static final long serialVersionUID = -3559296284004531576L;
	
	public TipoDiferenteUsuarioException(String mensagem) {
		super(mensagem);
	}
}

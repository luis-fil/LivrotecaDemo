package com.runapp.negocio.cadastro.exception;
/**
 * @author Luis Filipe
 * @version 1.00
 */
public class MensagemInexistenteException extends Exception {
	private static final long serialVersionUID = 4102842045541437348L;
	long id;
	
	public MensagemInexistenteException(long id) {
		super("A mensagem informada é inexistente");
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
}
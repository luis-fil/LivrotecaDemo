package com.runapp.negocio.fachada.exception;

/**
 * Essa é uma classe de exceção para quando um pedido vazio tentar ser finalizado
 * @author José Matheus
 * @version 1.0
 */
public class PedidoVazioException extends Exception {
	private static final long serialVersionUID = 4702801072836321138L;
	
	public PedidoVazioException() {
		super("O pedido não pode ser finalizado pois está vazio");
	}
}

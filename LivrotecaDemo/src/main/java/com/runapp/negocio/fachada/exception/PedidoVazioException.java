package com.runapp.negocio.fachada.exception;

public class PedidoVazioException extends Exception {
	private static final long serialVersionUID = 4702801072836321138L;
	
	public PedidoVazioException() {
		super("O pedido não pode ser finalizado pois está vazio");
	}
}

package com.runapp.negocio.cadastro.exception;

public class QuantidadeInsuficienteException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public QuantidadeInsuficienteException(int quantidadePedida, int quantidadeDisponivel) {
    	 super("Quantidade indisponível. Quantidade pedida: " + quantidadePedida + 
                 ", disponível: " + quantidadeDisponivel);
    }
}
package com.runapp.negocio.cadastro.exception;

/**
 * Essa é uma classe de exceção para quando uma qunatidade de livros no estoque não for suficiente pra alguma operação
 * @author Letícia Baracho
 * @version 1.0
 */
public class QuantidadeInsuficienteException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    public QuantidadeInsuficienteException(int quantidadePedida, int quantidadeDisponivel) {
    	 super("Quantidade indisponível. Quantidade pedida: " + quantidadePedida + 
                 ", disponível: " + quantidadeDisponivel);
    }
}
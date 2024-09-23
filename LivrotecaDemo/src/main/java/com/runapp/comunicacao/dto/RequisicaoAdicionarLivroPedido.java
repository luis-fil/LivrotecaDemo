package com.runapp.comunicacao.dto;

/**
 * Essa classe é um DTO para realizar a adição de um livro ao pedido de um cliente
 * @author José Matheus
 * @version 1.0
 */
public class RequisicaoAdicionarLivroPedido {
	private long idLivro;
	private int quantidade;
	private boolean isEbook;
	public long getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(long idLivro) {
		this.idLivro = idLivro;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public boolean isEbook() {
		return isEbook;
	}
	public void setEbook(boolean isEbook) {
		this.isEbook = isEbook;
	}
}

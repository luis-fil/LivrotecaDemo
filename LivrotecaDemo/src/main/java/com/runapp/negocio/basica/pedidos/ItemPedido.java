package com.runapp.negocio.basica.pedidos;

import com.runapp.negocio.basica.livros.Livro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Essa classe representa os itens de um pedido
 * @author José Matheus
 * @version 1.0
 */

@Entity
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Livro livro;
	private int quantidade;
	private boolean isEbook;
	
	public ItemPedido() {		
	}
	public ItemPedido(Livro livro, int quantidade, boolean isEbook) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.isEbook = isEbook;
	}
	
	public double getValorItem() {
		if (isEbook) {
			return livro.getValorEbook() * quantidade;
		} else {
			return livro.getValorLivroFisico() * quantidade;
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
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

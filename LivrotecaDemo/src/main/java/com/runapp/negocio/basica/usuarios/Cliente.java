package com.runapp.negocio.basica.usuarios;

import com.runapp.negocio.basica.pedidos.Pedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends Usuario{
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	@OneToOne(cascade = CascadeType.ALL)
	private Pedido pedidoPendente;
	
	public Cliente() {
		super();
	}
	public Cliente(String email, String senha, String nome, Endereco endereco) {
		super(email, senha, nome);
		this.endereco = endereco;
		this.pedidoPendente = new Pedido(this, "PENDENTE");
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Pedido getPedidoPendente() {
		return pedidoPendente;
	}
	public void setPedidoPendente(Pedido pedidoPendente) {
		this.pedidoPendente = pedidoPendente;
	}	
}

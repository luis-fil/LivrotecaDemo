package com.runapp.negocio.basica.pedidos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.runapp.negocio.basica.usuarios.Cliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JsonIgnoreProperties({"senha", "pedidoPendente"})
	private Cliente cliente;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemPedido> itens;
	private String status; // "PENDENTE" / "FINALIZADO"
	
	public Pedido() {
		this.itens = new ArrayList<ItemPedido>();
	}
	public Pedido(Cliente cliente, String status) {
		this.cliente = cliente;
		this.status = status;
		this.itens = new ArrayList<ItemPedido>();
	}
	
	public double getValorTotal() {
		double total = 0;
		for (ItemPedido item : itens) {
			total += item.getValorItem();
		}
		return total;
	}
	
	public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

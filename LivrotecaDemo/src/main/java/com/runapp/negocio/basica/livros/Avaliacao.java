package com.runapp.negocio.basica.livros;

import com.runapp.negocio.basica.usuarios.Cliente;

import jakarta.persistence.ManyToOne;

public class Avaliacao {
	private double nota;
	private String titulo;
	private String corpo;
	@ManyToOne
	private Cliente cliente;
	
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

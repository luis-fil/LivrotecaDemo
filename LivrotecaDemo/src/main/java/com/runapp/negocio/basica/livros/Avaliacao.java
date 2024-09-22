package com.runapp.negocio.basica.livros;

import com.runapp.negocio.basica.usuarios.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Essa classe cria um objeto Avaliação
 * @author Letícia Baracho
 * @version 1.0
 */

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double nota;
	private String titulo;
	private String corpo;
	@ManyToOne
	private Livro livro;
	@ManyToOne
	private Cliente cliente;
	
	public Avaliacao(String titulo, String corpo, double nota, Livro livro, Cliente cliente) {
		this.titulo = titulo;
		this.corpo = corpo;
		this.nota = nota;
		this.livro = livro;
		this.cliente = cliente;
	}
	
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
	
	public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
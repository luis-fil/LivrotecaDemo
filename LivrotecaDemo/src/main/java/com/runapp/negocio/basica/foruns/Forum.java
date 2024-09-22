package com.runapp.negocio.basica.foruns;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/**
 * Essa eh a classe basica de Forum
 * @author Luis Filipe
 * @version 1.03
 */
@Entity
public class Forum {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Forum(String titulo) {
		this.titulo = titulo;
	}
}

package com.runapp.negocio.basica.foruns;

import com.runapp.negocio.basica.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
/**
 * Essa eh a classe basica de Topico
 * @author Luis Filipe
 * @version 1.02
 */
@Entity
public class Topico {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	private String corpo;
	@ManyToOne
	private Usuario remetente;
	@ManyToOne
	private Forum forum;
	
	public Topico() {		
	}
	
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
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public Usuario getRemetente() {
		return remetente;
	}
	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Topico(Forum forum) {
		this.forum = forum;
	}
	
	public Topico(String titulo, String corpo, Usuario remetente, Forum forum){
		this.titulo = titulo;
		this.corpo = corpo;
		this.remetente = remetente;
		this.forum = forum;
	}
}

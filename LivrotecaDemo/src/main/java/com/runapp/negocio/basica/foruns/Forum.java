package com.runapp.negocio.basica.foruns;

import java.util.ArrayList;
import java.util.List;

public class Forum {
	private long id;
	private String titulo;
	private List<Topico> topicos;
	
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
	public List<Topico> getTopicos() {
		return topicos;
	}
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}
	
	public Forum() {
		super();
		this.topicos = new ArrayList<Topico>();
	}
	
	public Forum(String titulo) {
		this();
		this.titulo = titulo;
	}
	
	public void adicionarTopico(Topico t) {
		topicos.add(t);
	}
	
	public void removerTopico(Topico t) {
		topicos.remove(t);
	}
}

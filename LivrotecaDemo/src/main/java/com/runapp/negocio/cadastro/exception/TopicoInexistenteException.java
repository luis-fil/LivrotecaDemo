package com.runapp.negocio.cadastro.exception;

import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;

public class TopicoInexistenteException extends Exception {
	private static final long serialVersionUID = -4481824616630423954L;
	long id;
	Topico t;
	Mensagem m;
	String titulo;
	
	public TopicoInexistenteException(long id) {
		super("O Topico com o id inserido é inexistente");
		this.id = id;
	}
		
	public TopicoInexistenteException(Topico t) {
		super("O Topico inserido é inexistente");
		this.t = t;
	}
	
	public TopicoInexistenteException(String titulo) {
		super("Nenhum topico corresponde a busca");
		this.titulo = titulo;
	}

	public TopicoInexistenteException(Mensagem m) {
		super("O Topico dessa Mensagem é inexistente");
		this.m = m;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public Topico getTopico() {
		return t;
	}

	public Mensagem getMensagem() {
		return m;
	}

	public String getTitulo() {
		return titulo;
	}
	
	
}
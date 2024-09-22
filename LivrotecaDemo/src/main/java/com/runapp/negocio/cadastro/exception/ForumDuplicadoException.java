package com.runapp.negocio.cadastro.exception;
/**
 * @author Luis Filipe
 * @version 1.02
 */
public class ForumDuplicadoException extends Exception {
	private static final long serialVersionUID = -2477334368651453583L;
	
	long id;
	String titulo;
	
	public ForumDuplicadoException(long id) {
		super("O forum inserido já existe no sistema");
		this.id = id;
	}
	
	public ForumDuplicadoException(String titulo) {
		super("Já existe um forum com o nome informado");
		this.titulo = titulo;
	}

	public long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
}

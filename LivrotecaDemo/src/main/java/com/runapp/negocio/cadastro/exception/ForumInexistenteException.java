package com.runapp.negocio.cadastro.exception;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;

public class ForumInexistenteException extends Exception {
	private static final long serialVersionUID = -1616891515775298026L;
	
	long id;
	Forum f;
	
	public ForumInexistenteException(long id) {
		super("O forum com o id informado não existe");
		this.id = id;
	}
	
	public ForumInexistenteException(Forum f) {
		super("O forum inserido é inexistente");
		this.f = f;
	}
	
	public ForumInexistenteException(Topico t) {
		super("O Forum desse Topico é inexistente");
		this.f = t.getForum();
	}
	
	public ForumInexistenteException(Mensagem m) {
		super("O Forum dessa Mensagem é inexistente");
		this.f = m.getTopico().getForum();
	}

	public long getId() {
		return id;
	}
	
	public Forum getForum() {
		return f;
	}
}

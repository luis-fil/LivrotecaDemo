package com.runapp.negocio.basica.foruns;

import com.runapp.negocio.basica.usuarios.Usuario;

public class Mensagem {
	private String corpo;
	private Usuario rementente;
	
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public Usuario getRementente() {
		return rementente;
	}
	public void setRementente(Usuario rementente) {
		this.rementente = rementente;
	}
}

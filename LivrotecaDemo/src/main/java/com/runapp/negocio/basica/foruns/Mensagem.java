package com.runapp.negocio.basica.foruns;

import com.runapp.negocio.basica.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.util.Calendar;

@Entity
public class Mensagem {
	private String corpo;
	private Calendar data;
	@ManyToOne
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
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
}

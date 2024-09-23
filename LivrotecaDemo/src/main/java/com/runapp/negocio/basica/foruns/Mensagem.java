package com.runapp.negocio.basica.foruns;

import com.runapp.negocio.basica.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Calendar;
/**
 * Essa eh a classe basica de Mensagem
 * @author Luis Filipe
 * @version 1.02
 */
@Entity
public class Mensagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String corpo;
	private Calendar data;
	@ManyToOne
	private Usuario rementente;
	@ManyToOne
	private Topico topico;
	
	public Mensagem() {		
	}
	
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
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Mensagem(Topico topico) {
		this.topico = topico;
	}
	public Mensagem(String corpo, Calendar data, Usuario remetente, Topico topico) {
		this.corpo = corpo;
		this.data = data;
		this.rementente = remetente;
		this.topico = topico;
	}
}

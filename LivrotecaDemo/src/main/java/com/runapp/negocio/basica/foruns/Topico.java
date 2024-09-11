package com.runapp.negocio.basica.foruns;

import java.util.List;

import com.runapp.negocio.basica.usuarios.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;

@Entity
public class Topico {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private String titulo;
	private String corpo;
	@ManyToOne
	private Usuario remetente;
	@OneToMany
	private List<Mensagem> mensagens;
	
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
	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
	public Topico() {
		this.mensagens = new ArrayList<Mensagem>();
	}
	
	public Topico(String titulo, String corpo, Usuario remetente){
		this.titulo = titulo;
		this.corpo = corpo;
		this.remetente = remetente;
	}
	
	public void adicionarMensagem(Mensagem m) {
		mensagens.add(m);
	}
}

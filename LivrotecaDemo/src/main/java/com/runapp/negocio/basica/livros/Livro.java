package com.runapp.negocio.basica.livros;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Livro {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	private String autor;
	private double valorLivroFisico;
	private double valorEbook;
	private int numeroPaginas;
	private String genero;
	private String sinopse;
	private String editora;
	private int quantidade;
	@OneToMany
	private List<Avaliacao> avaliacoes;
	private double nota;
	
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
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public double getValorLivroFisico() {
		return valorLivroFisico;
	}
	
	public void setValorLivroFisico(double valorLivroFisico) {
		this.valorLivroFisico = valorLivroFisico;
	}
	
	public double getValorEbook() {
		return valorEbook;
	}
	
	public void setValorEbook(double valorEbook) {
		this.valorEbook = valorEbook;
	}
	
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getSinopse() {
		return sinopse;
	}
	
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora){
		this.editora = editora;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		this.nota = nota;
	}
}

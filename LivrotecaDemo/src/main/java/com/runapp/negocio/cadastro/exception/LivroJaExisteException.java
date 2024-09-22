package com.runapp.negocio.cadastro.exception;

/**
 * Essa é uma classe de exceção para quando um livro já existir no sistema
 * @author Letícia Baracho
 * @version 1.0
 */
public class LivroJaExisteException extends Exception {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String autor;

    public LivroJaExisteException(String titulo, String autor) {
        super("Já existe um livro com o título '" + titulo + "' e o autor '" + autor + "' no sistema.");
        this.titulo = titulo;
        this.autor = autor;
    }

	public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}

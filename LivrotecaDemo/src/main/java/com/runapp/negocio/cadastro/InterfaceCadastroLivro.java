package com.runapp.negocio.cadastro;

import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;
import com.runapp.negocio.cadastro.exception.QuantidadeInsuficienteException;

public interface InterfaceCadastroLivro {

	void adicionarLivro(String titulo, String autor, double valorLivroFisico, double valorEbook, int numeroPaginas,
			String genero, String sinopse, String editora, int quantidade);

	void atualizarLivro(Long idLivro, String novoTitulo, String novoAutor, double novoValorLivroFisico,
			double novoValorEbook, int novoNumeroPaginas, String novoGenero, String novaSinopse, String novaEditora,
			int novaQuantidade) throws LivroNaoExisteException;

	void avaliarLivro(Long idLivro, Cliente cliente, String titulo, String corpo, double nota)
			throws LivroNaoExisteException;

	void aumentarQuantidade(Long idLivro, int quantidade) throws LivroNaoExisteException;

	void diminuirQuantidade(Long idLivro, int quantidade)
			throws LivroNaoExisteException, QuantidadeInsuficienteException;

}
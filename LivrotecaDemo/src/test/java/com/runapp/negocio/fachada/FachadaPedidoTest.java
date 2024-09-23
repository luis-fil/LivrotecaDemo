package com.runapp.negocio.fachada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runapp.negocio.basica.livros.Livro;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Endereco;
import com.runapp.negocio.cadastro.exception.LivroJaExisteException;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;
import com.runapp.negocio.cadastro.exception.QuantidadeInsuficienteException;
import com.runapp.negocio.cadastro.exception.TipoDiferenteUsuarioException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.exception.ClienteNaoExisteException;
import com.runapp.negocio.fachada.exception.PedidoVazioException;
import com.runapp.negocio.fachada.exception.QuantidadeInvalidaException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FachadaPedidoTest {
	@Autowired
	private Fachada fachada;

	@BeforeAll
	public void init() throws LivroJaExisteException, UsuarioDuplicadoException {
		Cliente c = new Cliente("Matheus", "email@example.com", "123456", new Endereco("logradouro", "numero", "bairro", "cep", "uf"));
		
		fachada.adicionarLivro("titulo", "autor", 20.0, 10.0, 30, "genero", "sinopse", "editora", 10);
		fachada.cadastrarUsuario(c);
	}
	
	@Test
	void testeAdicionarLivroPedidoCliente() throws UsuarioNaoExisteException, ClienteNaoExisteException, LivroNaoExisteException, QuantidadeInvalidaException, QuantidadeInsuficienteException {
		Cliente c = fachada.procurarClienteId((long) 1);
		Livro l = fachada.procurarLivroId((long) 1);
		int quantItensPedidoCliente = c.getPedidoPendente().getItens().size();
		double valorPedido = c.getPedidoPendente().getValorTotal();
		
		fachada.adicionarLivroPedidoCliente(l.getId(), 1, false, c.getId());
		
		int quantItensPedidoCliente2 = c.getPedidoPendente().getItens().size();
		
		assertEquals(quantItensPedidoCliente + 1, quantItensPedidoCliente2);
		assertEquals(valorPedido + 20, c.getPedidoPendente().getValorTotal());
	}
	
	@Test
	void testeFinalizarPedidoCliente() throws UsuarioNaoExisteException, ClienteNaoExisteException, LivroNaoExisteException, QuantidadeInvalidaException, QuantidadeInsuficienteException, UsuarioDuplicadoException, PedidoVazioException, TipoDiferenteUsuarioException {
		Cliente c = fachada.procurarClienteId((long) 1);
		Livro l = fachada.procurarLivroId((long) 1);
		fachada.adicionarLivroPedidoCliente(l.getId(), 1, false, c.getId());
		
		double valor = fachada.finalizarPedidoCliente(c.getId());
		assertEquals(20, valor);
		assertEquals(c.getPedidoPendente().getItens().isEmpty(), true);
		assertEquals(c.getPedidoPendente().getStatus(), "PENDENTE");
		assertEquals(fachada.exibirHistoricoCliente(c.getId()).size(), 1);
	}
}

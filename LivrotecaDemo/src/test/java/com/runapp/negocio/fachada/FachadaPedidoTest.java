package com.runapp.negocio.fachada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runapp.negocio.basica.livros.Livro;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Endereco;
import com.runapp.negocio.cadastro.exception.LivroJaExisteException;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.exception.ClienteNaoExisteException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class FachadaPedidoTest {
	@Autowired
	private Fachada fachada;

	@BeforeEach
	public void init() throws LivroJaExisteException, UsuarioDuplicadoException {
		Cliente c = new Cliente("Matheus", "email@example.com", "123456", new Endereco("logradouro", "numero", "bairro", "cep", "uf"));
		
		fachada.adicionarLivro("titulo", "autor", 20.0, 10.0, 30, "genero", "sinopse", "editora", 10);
		fachada.cadastrarUsuario(c);
	}
	
	@Test
	void testeAdicionarLivroPedidoCliente() throws UsuarioNaoExisteException, ClienteNaoExisteException, LivroNaoExisteException {
		Cliente c = fachada.procurarClienteId((long) 1);
		Livro l = fachada.procurarLivroId((long) 1);
		int quantItensPedidoCliente = c.getPedidoPendente().getItens().size();
		
		fachada.adicionarLivroPedidoCliente(l.getId(), 1, false, c.getId());
		
		int quantItensPedidoCliente2 = c.getPedidoPendente().getItens().size();
		
		assertEquals(quantItensPedidoCliente + 1, quantItensPedidoCliente2);
	}

}

package com.runapp.negocio.fachada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Endereco;
import com.runapp.negocio.cadastro.exception.LivroJaExisteException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class FachadaPedidoTest {
	@Autowired
	private Fachada fachada;

	@BeforeEach
	public void init() throws LivroJaExisteException, UsuarioDuplicadoException {
		Cliente c = new Cliente("Matheus", "email@example.com", "123456", new Endereco("logradouro", "numero", "bairro", "cep", "uf"));
		
		fachada.adicionarLivro("titulo", "autor", 20.0, 10.0, 30, "genero", "sinopse", "editora", 4);
		fachada.cadastrarUsuario(c);
	}
	
	@Test
	void testeAdicionarLivroPedidoCliente() {
		fail("Not yet implemented");
	}

}

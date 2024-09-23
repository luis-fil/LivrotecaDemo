package com.runapp.negocio.cadastro;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runapp.negocio.basica.usuarios.Administrador;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Endereco;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;

/**
 * @author José Matheus
 * @version 1.0
 */
@SpringBootTest
class CadastroUsuarioTest {
	@Autowired
	private InterfaceCadastroUsuario cadastroUsuario;

	@Test
	void testarCadastroEmailDuplicado() {
		String email = "email@gmail.com";	
		Administrador a = new Administrador("Matheus", email, "12345678");
		Cliente c = new Cliente("Nogueira", email, "87654321", new Endereco("logradouro", "numero", "bairro", "cep", "uf"));
		
		UsuarioDuplicadoException exception = assertThrows(UsuarioDuplicadoException.class, () -> {
			cadastroUsuario.cadastrarUsuario(a);
			cadastroUsuario.cadastrarUsuario(c);
		});
		assertEquals(exception.getEmail(), email);
		assertTrue(exception.getMessage().contains("mesmo email"));
	}

}

package com.runapp.negocio.cadastro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.basica.usuarios.Administrador;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.exception.ForumDuplicadoException;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.ForumInvalidoException;
import com.runapp.negocio.cadastro.exception.MensagemInvalidaException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInvalidoException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;

@SpringBootTest
class CadastroMensagemTest {
	@Autowired
	private InterfaceCadastroMensagem colecaoMensagem;
	@Autowired
	private InterfaceCadastroTopico colecaoTopico;
	@Autowired
	private InterfaceCadastroForum colecaoForum;
	@Autowired
	private InterfaceCadastroUsuario colecaoUsuario;
	
	@Test
	void EnviarMensagem() throws ForumInvalidoException, ForumDuplicadoException, TopicoInvalidoException, ForumInexistenteException, MensagemInvalidaException, TopicoInexistenteException, UsuarioDuplicadoException {
		Forum f = new Forum("titulo");
		colecaoForum.salvarForum(f);
		Topico t = new Topico(f);
		colecaoTopico.salvarTopico(t);
		Usuario u = new Cliente();
		colecaoUsuario.cadastrarUsuario(u);
		Mensagem m = new Mensagem("bla bla", u, t);
		colecaoMensagem.salvarMensagem(m);
		assertEquals(1,colecaoMensagem.listarMensagens(t).size());
		assertEquals(m.getCorpo(), colecaoMensagem.localizarMensagemId(m.getId()).orElse(null).getCorpo());
	}
}

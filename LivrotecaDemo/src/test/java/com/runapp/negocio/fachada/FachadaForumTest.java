package com.runapp.negocio.fachada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Endereco;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.exception.ForumDuplicadoException;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.exception.MensagemVazioException;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class FachadaForumTest {
	@Autowired
	private Fachada fachada;

	@Test
	void SalvarTopicoUsuarioInexistente() throws ForumDuplicadoException, ForumInexistenteException, UsuarioNaoExisteException {
		Endereco e = new Endereco();
		Usuario u = new Cliente("nome", "email", "senha", e);
		Forum f = new Forum("titulo");
		fachada.salvarForum(f);
		assertEquals(f.getTitulo(), fachada.localizarForumId(f.getId()).getTitulo());
		Topico t = new Topico("titulot", "corpo", u, f);
		assertThrows(UsuarioNaoExisteException.class, ()->{fachada.salvarTopico(t);});
	}
	
	@Test
	void removerMensagemtest() throws UsuarioDuplicadoException, ForumDuplicadoException, ForumInexistenteException, UsuarioNaoExisteException, TopicoInexistenteException, MensagemVazioException {
		Usuario u = new Cliente();
		fachada.cadastrarUsuario(u);
		Forum f = new Forum("nome");
		fachada.salvarForum(f);
		Topico t = new Topico("tituloTopico", "corpoTopico", u,f);
		fachada.salvarTopico(t);
		Mensagem correta = new Mensagem("corpo", u, t);
		fachada.salvarMensagem(correta);
		Mensagem erro1 = new Mensagem("apagar m1", u, t);
		fachada.salvarMensagem(erro1);
		Mensagem erro2 = new Mensagem("apagar m2", u, t);
		fachada.salvarMensagem(erro2);
		assertEquals(3, fachada.listarMensagensTopico(t).size());
		fachada.removerMensagem("apagar");
		assertEquals(1, fachada.listarMensagensTopico(t).size());
	}
}

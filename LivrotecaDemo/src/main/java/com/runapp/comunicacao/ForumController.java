package com.runapp.comunicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.cadastro.exception.ForumDuplicadoException;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.MensagemInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.Fachada;
import com.runapp.negocio.fachada.exception.ForumVazioException;
import com.runapp.negocio.fachada.exception.MensagemVazioException;
import com.runapp.negocio.fachada.exception.TopicoVazioException;

/**
 * @author Luis Filipe 
 * @version 1.00
 */

@RestController
@RequestMapping("/api")
public class ForumController {
	@Autowired
	private Fachada fachada;
	
	@PostMapping("/admin/forum")
	public ResponseEntity<String> adicionarForum(@RequestBody Forum forum){
		try {
			fachada.salvarForum(forum);
			return ResponseEntity.ok("Forum inserido com sucesso!");
		} catch (NullPointerException e) {
			return ResponseEntity.badRequest().body("Forum inválido");
		} catch (ForumDuplicadoException e2) {
			return ResponseEntity.badRequest().body(e2.getMessage());
		}
	}
	
	@PostMapping("/usuario/topico")
	public ResponseEntity<String> adiconarTopico(@RequestBody Topico topico){
		try {
			fachada.salvarTopico(topico);
			return ResponseEntity.ok("Topico inserido com sucesso!");
		} catch (NullPointerException e1) {
			return ResponseEntity.badRequest().body("Topico invalido");
		} catch (UsuarioNaoExisteException e2) {
			return ResponseEntity.badRequest().body(e2.getMessage());
		} catch(ForumInexistenteException e3) {
			return ResponseEntity.badRequest().body(e3.getMessage());
		}
	}
	
	@PostMapping("/usuario/mensagem")
	public ResponseEntity<String> adicionarMensagem(@RequestBody Mensagem mensagem){
		try {
			fachada.salvarMensagem(mensagem);
			return ResponseEntity.ok("Mensagem enviada com sucesso!");
		} catch (NullPointerException e1) {
			return ResponseEntity.badRequest().body("Mensagem invalida");
		} catch (UsuarioNaoExisteException e2) {
			return ResponseEntity.badRequest().body(e2.getMessage());
		} catch (TopicoInexistenteException e3) {
			return ResponseEntity.badRequest().body(e3.getMessage());
		} catch (ForumInexistenteException e4) {
			return ResponseEntity.badRequest().body(e4.getMessage());
		}
	}
	
	@GetMapping("/usuario/forum")
	public ResponseEntity<?> listarForuns(){
		try {
			List<Forum> foruns = fachada.listarForuns();
			return ResponseEntity.ok(foruns);
		} catch (ForumVazioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/usuario/{id}/forum")
	public ResponseEntity<Forum> localizarForumId(@PathVariable long id){
		Forum forum = fachada.localizarForumId(id);
		if(forum == null) throw new NullPointerException("Forum nao encontrado");
		return ResponseEntity.ok(forum);
	}
	
	@GetMapping("/usuario/forum/topicos")
	public ResponseEntity<?> listarTopicosForum(@RequestBody Forum forum){
		try {
			List<Topico> topicos = fachada.listarTopicosForum(forum);
			return ResponseEntity.ok(topicos);
		} catch (TopicoVazioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/usuario/topico/busca")
	public ResponseEntity<?> procurarTopicos(String busca){
		try {
			List<Topico> topicos = fachada.procurarTopico(busca);
			return ResponseEntity.ok(topicos);
		} catch (TopicoInexistenteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/usuario/{id}/topico")
	public ResponseEntity<Topico> localizarTopicoId(@PathVariable long id){
		Topico topico = fachada.localizarTopicoId(id);
		if(topico == null) throw new NullPointerException("Topico nao encontrado");
		return ResponseEntity.ok(topico);
	}
	
	@GetMapping("/usuario/topico/mensagens")
	public ResponseEntity<?> listarMensagensTopico(@RequestBody Topico topico){
		try {
			List<Mensagem> mensagens = fachada.listarMensagensTopico(topico);
			return ResponseEntity.ok(mensagens);
		} catch (MensagemVazioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/usuario/{id}/mensagem")
	public ResponseEntity<Mensagem> localizarMensagemId(@PathVariable long id){
		Mensagem mensagem = fachada.localizarMensagemId(id);
		if(mensagem == null) throw new NullPointerException("Mensagem nao encontrada");
		return ResponseEntity.ok(mensagem);
	}
	
	@DeleteMapping("/admin/forum/remover")
	public ResponseEntity<String> removerForum(@RequestBody Forum forum){
		try {
			fachada.removerForum(forum);
			return ResponseEntity.ok("forum " + forum.getTitulo() + " removido com sucesso!");
		} catch (TopicoInexistenteException e1) {
			return ResponseEntity.badRequest().body(e1.getMessage());
		} catch (ForumInexistenteException e2) {
			return ResponseEntity.badRequest().body(e2.getMessage());
		}
	}
	
	@DeleteMapping("/admin/{id}/forum/remover")
	public ResponseEntity<String> removerForumId(@PathVariable long id){
		try {
			fachada.removerForumId(id);
			Forum forum = fachada.localizarForumId(id);
			return ResponseEntity.ok("forum " + forum.getTitulo() + " removido com sucesso!");
		} catch (TopicoInexistenteException e1) {
			return ResponseEntity.badRequest().body(e1.getMessage());
		} catch (ForumInexistenteException e2) {
			return ResponseEntity.badRequest().body(e2.getMessage());
		}
	}
	
	@DeleteMapping("/admin/topico/remover")
	public ResponseEntity<String> removerTopico(@RequestBody Topico topico){
		try {
			fachada.removerTopico(topico);
			return ResponseEntity.ok("topico " + topico.getTitulo() + " removido com sucesso!");
		} catch (TopicoInexistenteException e1) {
			return ResponseEntity.badRequest().body(e1.getMessage());
		}
	}
	
	@DeleteMapping("/admin/mensagem/remover")
	public ResponseEntity<String> removerMensagem(@RequestBody Mensagem mensagem){
		try {
			fachada.removerMensagem(mensagem);
			return ResponseEntity.ok("mensagem removida com sucesso!");
		} catch (MensagemInexistenteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/admin/{id}/mensagem/remover")
	public ResponseEntity<String> removerMensagemId(@PathVariable long id){
		try {
			fachada.removerMensagemId(id);
			return ResponseEntity.ok("mensagem removida com sucesso!");
		} catch (MensagemInexistenteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/admin/mensagem/busca/remover")
	public ResponseEntity<String> removerMensagem(@RequestBody String frase){
		fachada.removerMensagem(frase);
		return ResponseEntity.ok("mensagem removida com sucesso!");
	}
}

package com.runapp.comunicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runapp.negocio.basica.usuarios.Administrador;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.Fachada;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	public Fachada fachada;
	
	@PostMapping("/cliente")
	public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente c) {
		try {
			return ResponseEntity.ok((Cliente) fachada.cadastrarUsuario(c));
		} catch (UsuarioDuplicadoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/admin")
	public ResponseEntity<?> cadastrarAdministrador(@RequestBody Administrador a) {
		try {
			return ResponseEntity.ok((Administrador) fachada.cadastrarUsuario(a));
		} catch (UsuarioDuplicadoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> exibirUsuario(@PathVariable long id) {
		try {
			return ResponseEntity.ok(fachada.procurarUsuarioId(id));
		} catch (UsuarioNaoExisteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/admin/usuario/{email}")
	public ResponseEntity<?> procurarConta(@PathVariable String email) {
		try {
			return ResponseEntity.ok(fachada.procurarUsuarioEmail(email));
		} catch (UsuarioNaoExisteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> atualizarDadosCliente(@PathVariable long id, @RequestBody Cliente c) {
		c.setId(id);
		try {
			return ResponseEntity.ok((Cliente) fachada.salvarAlteracaoUsuario(c));
		} catch (UsuarioNaoExisteException | UsuarioDuplicadoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<?> atualizarDadosAdministrador(@PathVariable long id, @RequestBody Administrador a) {
		a.setId(id);
		try {
			return ResponseEntity.ok((Administrador) fachada.salvarAlteracaoUsuario(a));
		} catch (UsuarioNaoExisteException | UsuarioDuplicadoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}

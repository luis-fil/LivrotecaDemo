package com.runapp.comunicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runapp.negocio.basica.usuarios.Administrador;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.Fachada;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@Autowired
	public Fachada fachada;
	
	@PostMapping("/cliente")
	public Cliente cadastrarCliente(@RequestBody Cliente c) throws UsuarioDuplicadoException {
		return (Cliente) fachada.cadastrarUsuario(c);
	}
	
	@PostMapping("/admin")
	public Administrador cadastrarAdministrador(@RequestBody Administrador a) throws UsuarioDuplicadoException {
		return (Administrador) fachada.cadastrarUsuario(a);
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario exibirUsuario(@PathVariable long id) throws UsuarioNaoExisteException {
		return fachada.procurarUsuarioId(id);
	}
	
	@GetMapping("/admin/usuario/{email}")
	public Usuario procurarConta(@PathVariable String email) throws UsuarioNaoExisteException {
		return fachada.procurarUsuarioEmail(email);
	}
	
	@PutMapping("/cliente/{id}")
	public Cliente atualizarDadosCliente(@PathVariable long id, @RequestBody Cliente c) throws UsuarioNaoExisteException, UsuarioDuplicadoException {
		c.setId(id);
		return (Cliente) fachada.salvarAlteracaoUsuario(c);
	}
	
	@PutMapping("/admin/{id}")
	public Administrador atualizarDadosAdministrador(@PathVariable long id, @RequestBody Administrador a) throws UsuarioNaoExisteException, UsuarioDuplicadoException {
		a.setId(id);
		return (Administrador) fachada.salvarAlteracaoUsuario(a);
	}
}

package com.runapp.negocio.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioUsuario;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;

@Service
public class CadastroUsuario implements InterfaceCadastroUsuario {
	@Autowired
	private InterfaceRepositorioUsuario repositorioUsuario;

	@Override
	public Usuario procurarUsuarioEmail(String email) throws UsuarioNaoExisteException {
		Usuario u = repositorioUsuario.findByEmail(email);
		if (u == null) throw new UsuarioNaoExisteException(email);
		return u;
	}

	@Override
	public Usuario adicionarUsuario(Usuario usuario) throws UsuarioDuplicadoException {
		try {
			procurarUsuarioEmail(usuario.getEmail());
			throw new UsuarioDuplicadoException(usuario.getEmail());
		} catch(UsuarioNaoExisteException err) {
			return repositorioUsuario.save(usuario);
		}
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return repositorioUsuario.findAll();
	}

	@Override
	public Usuario procurarUsuarioId(Long id) {
		return repositorioUsuario.findById(id).orElse(null);
	}

	@Override
	public boolean existeUsuarioId(Long id) {
		return repositorioUsuario.existsById(id);
	}

	@Override
	public void salvarAlteracaoUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioDuplicadoException {
		Usuario u = procurarUsuarioId(usuario.getId());
		if (u == null) throw new UsuarioNaoExisteException(usuario.getId());
		if (u.getEmail() == usuario.getEmail()) {
			repositorioUsuario.save(usuario);			
		} else {
			try {
				procurarUsuarioEmail(usuario.getEmail());
				throw new UsuarioDuplicadoException(usuario.getEmail());
			} catch(UsuarioNaoExisteException err) {
				repositorioUsuario.save(usuario);
			}
		}
	}
}

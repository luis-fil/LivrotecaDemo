package com.runapp.negocio.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioUsuario;
import com.runapp.negocio.basica.usuarios.Administrador;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.exception.TipoDiferenteUsuarioException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;

/**
 * Essa classe implementa os métodos necessários para manipulação do usuário
 * @author José Matheus
 * @version 1.0
 */
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
	public Usuario cadastrarUsuario(Usuario usuario) throws UsuarioDuplicadoException {
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
	public Usuario procurarUsuarioId(Long id) throws UsuarioNaoExisteException {
		Usuario u = repositorioUsuario.findById(id).orElse(null);
		if (u == null) throw new UsuarioNaoExisteException(id);
		return u;
	}

	@Override
	public boolean existeUsuarioId(Long id) {
		return repositorioUsuario.existsById(id);
	}

	/**
	 *  Método utilizado para salvar um usuário que sofreu alguma alteração.
	 *  Qualquer informação do usuário pode ser alterada, menos seu id, por isso há 
	 *  verificações caso mude o email.
	 */
	@Override
	public Usuario salvarAlteracaoUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioDuplicadoException, TipoDiferenteUsuarioException {
		Usuario u = procurarUsuarioId(usuario.getId());
		if (u instanceof Cliente && !(usuario instanceof Cliente)) {
			throw new TipoDiferenteUsuarioException(String.format("O usuario informado para alterar o cliente com id %d não é um cliente", usuario.getId()));
		}
		if (u instanceof Administrador && !(usuario instanceof Administrador)) {
			throw new TipoDiferenteUsuarioException(String.format("O usuario informado para alterar o administrador com id %d não é um administrador", usuario.getId()));
		}
		if (u.getEmail().equals(usuario.getEmail())) {
			return repositorioUsuario.save(usuario);			
		} else {
			try {
				procurarUsuarioEmail(usuario.getEmail());
				throw new UsuarioDuplicadoException(usuario.getEmail());
			} catch(UsuarioNaoExisteException err) {
				return repositorioUsuario.save(usuario);
			}
		}
	}
}

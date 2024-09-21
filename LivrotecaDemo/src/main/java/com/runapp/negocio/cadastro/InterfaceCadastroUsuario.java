package com.runapp.negocio.cadastro;

import java.util.List;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;

public interface InterfaceCadastroUsuario {

	Usuario procurarUsuarioEmail(String email) throws UsuarioNaoExisteException;

	Usuario adicionarUsuario(Usuario usuario) throws UsuarioDuplicadoException;
	
	void salvarAlteracaoUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioDuplicadoException;

	List<Usuario> listarUsuarios();

	Usuario procurarUsuarioId(Long id);

	boolean existeUsuarioId(Long id);

}
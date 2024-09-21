package com.runapp.negocio.cadastro;

import java.util.List;

import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;

public interface InterfaceCadastroUsuario {

	Usuario procurarUsuarioEmail(String email) throws UsuarioNaoExisteException;

	Usuario salvarUsuario(Usuario usuario) throws UsuarioDuplicadoException;

	List<Usuario> listarUsuarios();

	Usuario procurarUsuarioId(Long id);

	boolean existeUsuarioId(Long id);

	void removerUsuarioEmail(String email) throws UsuarioNaoExisteException;

}
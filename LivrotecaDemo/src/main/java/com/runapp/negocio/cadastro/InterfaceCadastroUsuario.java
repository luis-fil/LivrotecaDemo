package com.runapp.negocio.cadastro;

import java.util.List;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.exception.TipoDiferenteUsuarioException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;

/**
 * @author José Matheus
 * @version 1.0
 */
public interface InterfaceCadastroUsuario {

	Usuario procurarUsuarioEmail(String email) throws UsuarioNaoExisteException;

	Usuario cadastrarUsuario(Usuario usuario) throws UsuarioDuplicadoException;
	
	Usuario salvarAlteracaoUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioDuplicadoException, TipoDiferenteUsuarioException;

	List<Usuario> listarUsuarios();

	Usuario procurarUsuarioId(Long id) throws UsuarioNaoExisteException;

	boolean existeUsuarioId(Long id);

}
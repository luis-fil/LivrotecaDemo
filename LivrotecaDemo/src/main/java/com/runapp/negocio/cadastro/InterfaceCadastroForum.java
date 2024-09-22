package com.runapp.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.cadastro.exception.ForumDuplicadoException;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.ForumInvalidoException;

/**
 * @author Luis Filipe
 * @version 1.00
 */
public interface InterfaceCadastroForum {
	Forum salvarForum(Forum f) throws ForumInvalidoException, ForumDuplicadoException;
	List<Forum> listarForuns();
	void removerForumId(long id) throws ForumInexistenteException;
	void removerForum(Forum f) throws ForumInexistenteException;
	Optional<Forum> localizarForumId(long id);
}

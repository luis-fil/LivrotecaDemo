package com.runapp.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import com.runapp.negocio.basica.foruns.Forum;

public interface InterfaceCadastroForum {

	Forum salvarForum(Forum f);
	List<Forum> listarForuns();
	void removerForumId(long id);
	void removerFoum(Forum f);
	Optional<Forum> localizarForumId(long id);
}

package com.runapp.dados;

import java.util.List;
import java.util.Optional;

import com.runapp.negocio.basica.foruns.Forum;

public interface InterfaceRepositorioForum {
	Forum salvarForum(Forum f);
	
	List<Forum> listarFouns();
	
	Optional<Forum> localizarForumId(long id);
}

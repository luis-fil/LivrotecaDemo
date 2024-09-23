package com.runapp.negocio.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioForum;
import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.cadastro.exception.ForumDuplicadoException;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
/**
 * Essa classe gerencia o cadastro no repositorio de Forum
 * @author Luis Filipe
 * @version 1.06
 */
@Service
public class CadastroForum implements InterfaceCadastroForum{
	@Autowired
	private InterfaceRepositorioForum colecaoForum;

	public Forum localizarForumId(long id) {
		return colecaoForum.findById(id).orElse(null);
	}
	
	public Forum salvarForum(Forum f) throws ForumDuplicadoException{
		if(localizarForumId(f.getId())!= null) throw new ForumDuplicadoException(f.getId());
		if(colecaoForum.findByTitulo(f.getTitulo()).isPresent()) throw new ForumDuplicadoException(f.getTitulo());
		return colecaoForum.save(f);
	}
	
	public List<Forum> listarForuns() {
		return colecaoForum.findAll();
	}

	public void removerForumId(long id) throws ForumInexistenteException{
		if(localizarForumId(id) == null) throw new ForumInexistenteException(id);
		colecaoForum.deleteById(id);
	}

	public void removerForum(Forum f) throws ForumInexistenteException{
		if(localizarForumId(f.getId()) == null) throw new ForumInexistenteException(f);
		colecaoForum.delete(f);
	}	
}

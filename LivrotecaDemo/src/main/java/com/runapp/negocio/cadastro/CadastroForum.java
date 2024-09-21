package com.runapp.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioForum;
import com.runapp.negocio.basica.foruns.Forum;

@Service
public class CadastroForum implements InterfaceCadastroForum{
	@Autowired
	private InterfaceRepositorioForum colecaoForum;

	@Override
	public Forum salvarForum(Forum f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Forum> listarForuns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removerForumId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerFoum(Forum f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Forum> localizarForumId(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	
}

package com.runapp.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioForum;
import com.runapp.dados.InterfaceRepositorioTopico;
import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;
/**
 * Essa classe gerencia o cadastro no repositorio de Topico
 * @author Luis Filipe
 * @version 1.06
 */
@Service
public class CadastroTopico implements InterfaceCadastroTopico{
	@Autowired
	private InterfaceRepositorioTopico colecaoTopico;
	@Autowired
	private InterfaceRepositorioForum colecaoForum;
	
	public List<Topico> procurarTopicoTitulo(String titulo) throws TopicoInexistenteException{
		List<Topico> busca = colecaoTopico.findByTituloContainingIgnoreCase(titulo);
		if(busca == null) throw new TopicoInexistenteException(titulo);
		return busca;
	}
	
	public Optional<Topico> localizarTopicoId(long id){
		return colecaoTopico.findById(id);
	}

	public Topico salvarTopico(Topico t) throws ForumInexistenteException{
		if(colecaoForum.findById(t.getForum().getId()) == null) throw new ForumInexistenteException(t);
		return colecaoTopico.save(t);
	}
	
	public List<Topico> listarTopicosForum(Forum f) {
		return colecaoTopico.findByForum(f);
	}

	public void removerTopicoId(long id) throws TopicoInexistenteException{
		if(localizarTopicoId(id) == null) throw new TopicoInexistenteException(id);
		colecaoTopico.deleteById(id);
	}

	public void removerTopico(Topico t) throws TopicoInexistenteException{
		if(localizarTopicoId(t.getId()) == null) throw new TopicoInexistenteException(t);
		colecaoTopico.delete(t);
	}	
}

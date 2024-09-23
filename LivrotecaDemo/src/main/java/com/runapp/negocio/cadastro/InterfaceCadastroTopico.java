package com.runapp.negocio.cadastro;

import java.util.List;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;

/**
 * @author Luis Filipe
 * @version 1.00
 */
public interface InterfaceCadastroTopico {
	List<Topico> procurarTopicoTitulo(String titulo) throws TopicoInexistenteException;
	Topico localizarTopicoId(long id);
	Topico salvarTopico(Topico t) throws ForumInexistenteException;
	List<Topico> listarTopicosForum(Forum f);
	void removerTopicoId(long id) throws TopicoInexistenteException;
	void removerTopico(Topico f) throws TopicoInexistenteException;
}

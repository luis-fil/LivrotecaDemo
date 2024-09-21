package com.runapp.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.ForumInvalidoException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInvalidoException;

public interface InterfaceCadastroTopico {
	List<Topico> procurarTopicoTitulo(String titulo) throws TopicoInexistenteException;
	Optional<Topico> localizarTopicoId(long id);
	Topico salvarTopico(Topico t) throws TopicoInvalidoException, ForumInvalidoException, ForumInexistenteException;
	List<Topico> listarTopicosForum(Forum f);
	void removerTopicoId(long id) throws TopicoInexistenteException;
	void removerTopico(Topico f) throws TopicoInexistenteException;
}

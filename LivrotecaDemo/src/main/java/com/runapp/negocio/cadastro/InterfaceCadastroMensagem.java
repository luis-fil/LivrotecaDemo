package com.runapp.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.ForumInvalidoException;
import com.runapp.negocio.cadastro.exception.MensagemInexistenteException;
import com.runapp.negocio.cadastro.exception.MensagemInvalidaException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInvalidoException;

public interface InterfaceCadastroMensagem {
	Optional<Mensagem> localizarMensagemId(long id);
	Mensagem salvarMensagem(Mensagem m) throws MensagemInvalidaException, TopicoInvalidoException, ForumInvalidoException, TopicoInexistenteException, ForumInexistenteException;
	List<Mensagem> listarMensagens(Topico t);
	void removerMensagemId(long id) throws MensagemInexistenteException;
	void removerMensagem(String frase);
}

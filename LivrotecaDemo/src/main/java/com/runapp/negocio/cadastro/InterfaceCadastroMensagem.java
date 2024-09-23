package com.runapp.negocio.cadastro;

import java.util.List;

import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.MensagemInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;

/**
 * @author Luis Filipe
 * @version 1.0o
 */
public interface InterfaceCadastroMensagem {
	Mensagem localizarMensagemId(long id);
	Mensagem salvarMensagem(Mensagem m) throws TopicoInexistenteException, ForumInexistenteException;
	List<Mensagem> listarMensagens(Topico t);
	void removerMensagemId(long id) throws MensagemInexistenteException;
	void removerMensagem(String frase);
	void removerMensagem(Mensagem mensagem) throws MensagemInexistenteException;
}

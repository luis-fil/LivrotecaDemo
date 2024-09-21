package com.runapp.negocio.cadastro;

import java.util.List;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioForum;
import com.runapp.dados.InterfaceRepositorioMensagem;
import com.runapp.dados.InterfaceRepositorioTopico;
import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.cadastro.exception.ForumInexistenteException;
import com.runapp.negocio.cadastro.exception.ForumInvalidoException;
import com.runapp.negocio.cadastro.exception.MensagemInexistenteException;
import com.runapp.negocio.cadastro.exception.MensagemInvalidaException;
import com.runapp.negocio.cadastro.exception.TopicoInexistenteException;
import com.runapp.negocio.cadastro.exception.TopicoInvalidoException;

@Service
public class CadastroMensagem implements InterfaceCadastroMensagem{
	@Autowired
	private InterfaceRepositorioMensagem colecaoMensagem;
	@Autowired
	private InterfaceRepositorioTopico colecaoTopico;
	@Autowired
	private InterfaceRepositorioForum colecaoForum;

	public Mensagem localizarMensagemId(long id) {
		return colecaoMensagem.findById(id);
	}

	public Mensagem salvarMensagem(Mensagem m) throws MensagemInvalidaException, TopicoInvalidoException, ForumInvalidoException, TopicoInexistenteException, ForumInexistenteException{
		if(m == null) throw new MensagemInvalidaException();
		if(m.getTopico() == null) throw new TopicoInvalidoException();
		if(colecaoTopico.findById(m.getTopico().getId()) == null) throw new TopicoInexistenteException(m);
		if(m.getTopico().getForum() == null) throw new ForumInvalidoException();
		if(colecaoForum.findById(m.getTopico().getForum().getId()) == null) throw new ForumInexistenteException(m);
		return colecaoMensagem.save(m);
	}

	public List<Mensagem> listarMensagens(Topico t) {
		return colecaoMensagem.findByTopico(t);
	}

	public void removerMensagemId(long id) throws MensagemInexistenteException {
		if(localizarMensagemId(id) == null) throw new MensagemInexistenteException(id);
		colecaoMensagem.deleteById(id);
	}
	
	public void removerMensagem(String frase) {
		colecaoMensagem.deleteAllByFraseContaining(frase);
	}	
}

package com.runapp.negocio.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;
import com.runapp.negocio.basica.livros.Livro;
import com.runapp.negocio.basica.pedidos.ItemPedido;
import com.runapp.negocio.basica.pedidos.Pedido;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.InterfaceCadastroPedido;
import com.runapp.negocio.cadastro.InterfaceCadastroTopico;
import com.runapp.negocio.cadastro.InterfaceCadastroUsuario;
import com.runapp.negocio.cadastro.exception.*;
import com.runapp.negocio.fachada.exception.ClienteNaoExisteException;
import com.runapp.negocio.cadastro.InterfaceCadastroForum;
import com.runapp.negocio.cadastro.InterfaceCadastroLivro;
import com.runapp.negocio.cadastro.InterfaceCadastroMensagem;

@Service
public class Fachada {
	@Autowired
	private InterfaceCadastroUsuario cadastroUsuario;
	@Autowired
	private InterfaceCadastroPedido cadastroPedido;
	@Autowired
	private InterfaceCadastroLivro cadastroLivro;
	@Autowired
	private InterfaceCadastroForum cadastroForum;
	@Autowired
	private InterfaceCadastroTopico cadastroTopico;
	@Autowired
	private InterfaceCadastroMensagem cadastroMensagem;
	
	// Usuario
	public Usuario procurarUsuarioEmail(String email) throws UsuarioNaoExisteException {
		return cadastroUsuario.procurarUsuarioEmail(email);
	}
	public Usuario cadastrarUsuario(Usuario usuario) throws UsuarioDuplicadoException {
		return cadastroUsuario.cadastrarUsuario(usuario);
	}
	public List<Usuario> listarUsuarios() {
		return cadastroUsuario.listarUsuarios();
	}
	public Usuario procurarUsuarioId(Long id) throws UsuarioNaoExisteException {
		return cadastroUsuario.procurarUsuarioId(id);
	}
	public boolean existeUsuarioId(Long id) {
		return cadastroUsuario.existeUsuarioId(id);
	}
	public void salvarAlteracaoUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioDuplicadoException {
		cadastroUsuario.salvarAlteracaoUsuario(usuario);
	}
	public Cliente procurarClienteId(Long id) throws UsuarioNaoExisteException, ClienteNaoExisteException {
		Usuario u = procurarUsuarioId(id);
		if (!(u instanceof Cliente)) throw new ClienteNaoExisteException(id);
		return (Cliente) u;
	}
	
	// Pedido
	public List<Pedido> exibirHistoricoCliente(Long idCliente) {
		return cadastroPedido.exibirHistoricoCliente(idCliente);
	}
	public List<Pedido> exibirExtrato() {
		return cadastroPedido.exibirExtrato();
	}
	public Pedido salvarPedido(Pedido pedido) {
		return cadastroPedido.salvarPedido(pedido);
	}
	public Pedido procurarPedidoId(Long id) {
		return cadastroPedido.procurarPedidoId(id);
	}
	public boolean existePedidoId(Long id) {
		return cadastroPedido.existePedidoId(id);
	}
	public void removerPedido(Pedido pedido) {
		cadastroPedido.removerPedido(pedido);
	}
	
	//Livro
	public void adicionarLivro(String titulo, String autor, double valorLivroFisico, double valorEbook, 
            int numeroPaginas, String genero, String sinopse, String editora, int quantidade) throws LivroJaExisteException {
        cadastroLivro.adicionarLivro(titulo, autor, valorLivroFisico, valorEbook, numeroPaginas, genero, sinopse, editora, quantidade);
    }
    public void atualizarLivro(Long idLivro, String novoTitulo, String novoAutor, double novoValorLivroFisico, 
            double novoValorEbook, int novoNumeroPaginas, String novoGenero, 
            String novaSinopse, String novaEditora, int novaQuantidade) throws LivroNaoExisteException {
        cadastroLivro.atualizarLivro(idLivro, novoTitulo, novoAutor, novoValorLivroFisico, novoValorEbook, 
                novoNumeroPaginas, novoGenero, novaSinopse, novaEditora, novaQuantidade);
    }
    public void avaliarLivro(Long idLivro, Cliente cliente, String titulo, String corpo, double nota) throws LivroNaoExisteException {
        cadastroLivro.avaliarLivro(idLivro, cliente, titulo, corpo, nota);
    }
    public void removerAvaliacao(Long idLivro, Long idAvaliacao) throws LivroNaoExisteException, AvaliacaoNaoExisteException {
    	cadastroLivro.removerAvaliacao(idLivro, idAvaliacao);
    }
    
    public void aumentarQuantidade(Long idLivro, int quantidade) throws LivroNaoExisteException {
        cadastroLivro.aumentarQuantidade(idLivro, quantidade);
    }
    public void diminuirQuantidade(Long idLivro, int quantidade) throws LivroNaoExisteException, QuantidadeInsuficienteException {
        cadastroLivro.diminuirQuantidade(idLivro, quantidade);
    }
    public List<Livro> listarLivros() {
        return cadastroLivro.listarLivros();
    }
    public Livro procurarLivroId(Long id) throws LivroNaoExisteException {
        return cadastroLivro.procurarLivroId(id);
    }
    public Livro procurarLivroTitulo(String titulo) throws LivroNaoExisteException {
        return cadastroLivro.procurarLivroTitulo(titulo);
    }
    public List<Livro> procurarLivroAutor(String autor) throws LivroNaoExisteException {
        return cadastroLivro.procurarLivroAutor(autor);
    }
    public List<Livro> procurarLivroGenero(String genero) throws LivroNaoExisteException {
        return cadastroLivro.procurarLivroGenero(genero);
    }
    
	// Negocio Usuario-Pedido
	public void cancelarPedidoCliente(Long id) throws UsuarioNaoExisteException, ClienteNaoExisteException, UsuarioDuplicadoException {
		Cliente cliente = procurarClienteId(id);
		
		Pedido pedido = cliente.getPedidoPendente();
		removerPedido(pedido);
		
		cliente.setPedidoPendente(new Pedido(cliente, "PENDENTE"));
		salvarAlteracaoUsuario(cliente);
	}
	public void finalizarPedidoCliente(Long id) throws UsuarioNaoExisteException, ClienteNaoExisteException, UsuarioDuplicadoException {
		Cliente cliente = procurarClienteId(id);
		
		Pedido pedido = cliente.getPedidoPendente();
		pedido.setStatus("FINALIZADO");
		salvarPedido(pedido);
		
		cliente.setPedidoPendente(new Pedido(cliente, "PENDENTE"));
		salvarAlteracaoUsuario(cliente);
	}
	
	// Negocio Livro-Pedido-Cliente
	public void adicionarLivroPedidoCliente(Long idLivro, int quantidade, boolean isEbook, Long idCliente) throws UsuarioNaoExisteException, ClienteNaoExisteException, LivroNaoExisteException {
		Cliente cliente = procurarClienteId(idCliente);
		
		Livro livro = procurarLivroId(idLivro);
		ItemPedido item = new ItemPedido(livro, quantidade, isEbook);
		
		Pedido pedido = cliente.getPedidoPendente();
		pedido.adicionarItem(item);
		salvarPedido(pedido);
	}
	
	// Forum - SALVAR
	public Forum salvarForum(Forum forum) throws ForumInvalidoException, ForumDuplicadoException {
		if(forum == null) throw new ForumInvalidoException();
		return cadastroForum.salvarForum(forum);
	}
	public Topico salvarTopico(Topico topico) throws TopicoInvalidoException, ForumInvalidoException, ForumInexistenteException, UsuarioNaoExisteException, UsuarioInvalidoException {
		if(topico == null) throw new TopicoInvalidoException();
		Usuario u = cadastroUsuario.procurarUsuarioId(topico.getRemetente().getId());
		if (u == null) throw new UsuarioInvalidoException();
		if(topico.getForum() == null) throw new ForumInvalidoException(topico);
		return cadastroTopico.salvarTopico(topico);
	}
	public Mensagem salvarMensagem(Mensagem mensagem) throws MensagemInvalidaException, TopicoInvalidoException, ForumInvalidoException, TopicoInexistenteException, ForumInexistenteException, NaoEhClienteException, UsuarioInvalidoException {
		if(mensagem == null) throw new MensagemInvalidaException();
		if(mensagem.getTopico() == null) throw new TopicoInvalidoException();
		if(mensagem.getTopico().getForum() == null) throw new ForumInvalidoException();
		Usuario u = cadastroUsuario.procurarUsuarioId(mensagem.getRementente().getId());
		if(u == null) throw new UsuarioInvalidoException();
		return cadastroMensagem.salvarMensagem(mensagem);
	}
	
	// Forum - LISTAR
	public List<Forum> listarForuns(){
		return cadastroForum.listarForuns();
	}
	public List<Topico> listarTopicosForum(Forum forum){
		return cadastroTopico.listarTopicosForum(forum);
	}
	public List<Mensagem> listarMensagensTopico(Topico topico){
		return cadastroMensagem.listarMensagens(topico);
	}
	
	// Forum - REMOVER
	public void removerForum(Forum forum) throws ForumInexistenteException, TopicoInexistenteException {
		List<Topico> topicos = cadastroTopico.listarTopicosForum(forum);
		List<Mensagem> mensagens;
		for(Topico t: topicos) {
			mensagens = cadastroMensagem.listarMensagens(t);
			mensagens.clear();
			cadastroTopico.removerTopico(t);
		}
		cadastroForum.removerForum(forum);
	}
	public void removerForumId(long id) throws ForumInexistenteException, TopicoInexistenteException {
		Forum f = cadastroForum.localizarForumId(id).orElse(null);
		if(f!=null) {
			List<Topico> topicos = cadastroTopico.listarTopicosForum(f);
			List<Mensagem> mensagens;
			for(Topico t: topicos) {
				mensagens = cadastroMensagem.listarMensagens(t);
				mensagens.clear();
				cadastroTopico.removerTopico(t);
			}
			cadastroForum.removerForumId(id);
		} else throw new ForumInexistenteException(f);
	}
	public void removerTopico(Topico topico) throws TopicoInexistenteException, TopicoInvalidoException {
		if(topico != null) {
			cadastroMensagem.listarMensagens(topico).clear();
			cadastroTopico.removerTopico(topico);
		} else throw new TopicoInvalidoException();
	}
	public void removerTopicoId(long id) throws TopicoInexistenteException, TopicoInvalidoException {
		Topico topico = cadastroTopico.localizarTopicoId(id).orElse(null);
		if(topico != null) {
			cadastroMensagem.listarMensagens(topico).clear();
			cadastroTopico.removerTopico(topico);
		} else throw new TopicoInvalidoException();
	}
	public void removerMensagemId(long id) throws MensagemInexistenteException {
		cadastroMensagem.removerMensagemId(id);
	}
	public void removerMensagem(String frase) {
		cadastroMensagem.removerMensagem(frase);
	}
	
	// Forum - BUSCA
	public Forum localizarForumId(long id) {
		return cadastroForum.localizarForumId(id).orElse(null);
	}
	public Topico localizarTopicoId(long id) {
		return cadastroTopico.localizarTopicoId(id).orElse(null);
	}
	public Mensagem localizarMensagemId(long id) {
		return cadastroMensagem.localizarMensagemId(id).orElse(null);
	}
	// busca por parametro
	public List<Topico> procurarTopico(String busca) throws TopicoInexistenteException {
		return cadastroTopico.procurarTopicoTitulo(busca);
	}
}
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
import com.runapp.negocio.fachada.exception.ForumVazioException;
import com.runapp.negocio.fachada.exception.MensagemVazioException;
import com.runapp.negocio.fachada.exception.PedidoVazioException;
import com.runapp.negocio.fachada.exception.QuantidadeInvalidaException;
import com.runapp.negocio.fachada.exception.TopicoVazioException;
import com.runapp.negocio.cadastro.InterfaceCadastroForum;
import com.runapp.negocio.cadastro.InterfaceCadastroLivro;
import com.runapp.negocio.cadastro.InterfaceCadastroMensagem;

/**
 * @author José Matheus, Letícia Baracho e Luís Filipe
 * @version 1.00
 */
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
	public Usuario salvarAlteracaoUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioDuplicadoException, TipoDiferenteUsuarioException {
		return cadastroUsuario.salvarAlteracaoUsuario(usuario);
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
    
	// Negocio Cliente-Pedido
	public void cancelarPedidoCliente(Long id) throws UsuarioNaoExisteException, ClienteNaoExisteException, UsuarioDuplicadoException, TipoDiferenteUsuarioException {
		Cliente cliente = procurarClienteId(id);
		
		Pedido pedido = cliente.getPedidoPendente();
		removerPedido(pedido);
		
		cliente.setPedidoPendente(new Pedido(cliente, "PENDENTE"));
		salvarAlteracaoUsuario(cliente);
	}
	public double finalizarPedidoCliente(Long id) throws UsuarioNaoExisteException, ClienteNaoExisteException, UsuarioDuplicadoException, LivroNaoExisteException, QuantidadeInsuficienteException, PedidoVazioException, TipoDiferenteUsuarioException {
		Cliente cliente = procurarClienteId(id);
		
		Pedido pedido = cliente.getPedidoPendente();
		if (pedido.getItens().isEmpty()) throw new PedidoVazioException();
		for (ItemPedido item : pedido.getItens()) {
			diminuirQuantidade(item.getLivro().getId(), item.getQuantidade());
		}
		double valorTotal = pedido.getValorTotal();
		
		pedido.setStatus("FINALIZADO");
		salvarPedido(pedido);
		
		cliente.setPedidoPendente(new Pedido(cliente, "PENDENTE"));
		salvarAlteracaoUsuario(cliente);
		return valorTotal;
	}
	
	// Negocio Livro-Pedido-Cliente
	public void adicionarLivroPedidoCliente(Long idLivro, int quantidade, boolean isEbook, Long idCliente) throws UsuarioNaoExisteException, ClienteNaoExisteException, LivroNaoExisteException, QuantidadeInvalidaException, QuantidadeInsuficienteException {
		Cliente cliente = procurarClienteId(idCliente);
		
		Livro livro = procurarLivroId(idLivro);
		if (quantidade < 1) throw new QuantidadeInvalidaException(quantidade);
		if (quantidade > livro.getQuantidade()) throw new QuantidadeInsuficienteException(quantidade, livro.getQuantidade());
		ItemPedido item = new ItemPedido(livro, quantidade, isEbook);
		
		Pedido pedido = cliente.getPedidoPendente();
		pedido.adicionarItem(item);
		salvarPedido(pedido);
	}
	
	// Forum - SALVAR
	public Forum salvarForum(Forum forum) throws NullPointerException, ForumDuplicadoException {
		if(forum == null) throw new NullPointerException("Forum invalido");
		return cadastroForum.salvarForum(forum);
	}
	public Topico salvarTopico(Topico topico) throws ForumInexistenteException, UsuarioNaoExisteException, NullPointerException{
		if(topico == null) throw new NullPointerException("Topico invalido");
		procurarUsuarioId(topico.getRemetente().getId());
		if(topico.getForum() == null) throw new NullPointerException("Forum invalido");
		return cadastroTopico.salvarTopico(topico);
	}
	public Mensagem salvarMensagem(Mensagem mensagem) throws TopicoInexistenteException, ForumInexistenteException, UsuarioNaoExisteException, NullPointerException {
		if(mensagem == null) throw new NullPointerException("Mensagem invalida");
		if(mensagem.getTopico() == null) throw new NullPointerException("Topico invalido");
		if(mensagem.getTopico().getForum() == null) throw new NullPointerException("Forum invalido");
		procurarUsuarioId(mensagem.getRementente().getId());
		return cadastroMensagem.salvarMensagem(mensagem);
	}
	
	// Forum - LISTAR
	public List<Forum> listarForuns() throws ForumVazioException{
		List<Forum> foruns = cadastroForum.listarForuns();
		if(foruns.size() == 0) throw new ForumVazioException();
		return foruns;
	}
	public List<Topico> listarTopicosForum(Forum forum) throws TopicoVazioException{
		List<Topico> topicos = cadastroTopico.listarTopicosForum(forum);
		if(topicos.size() == 0) throw new TopicoVazioException();
		return topicos;
	}
	public List<Mensagem> listarMensagensTopico(Topico topico) throws MensagemVazioException{
		List<Mensagem> mensagens = cadastroMensagem.listarMensagens(topico);
		if(mensagens.size() == 0) throw new MensagemVazioException();
		return mensagens;
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
		Forum f = cadastroForum.localizarForumId(id);
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
	public void removerTopico(Topico topico) throws TopicoInexistenteException{
		if(topico != null) {
			cadastroMensagem.listarMensagens(topico).clear();
			cadastroTopico.removerTopico(topico);
		} else throw new NullPointerException("Topico invalido");
	}
	public void removerTopicoId(long id) throws TopicoInexistenteException{
		Topico topico = cadastroTopico.localizarTopicoId(id);
		if(topico != null) {
			cadastroMensagem.listarMensagens(topico).clear();
			cadastroTopico.removerTopico(topico);
		} else throw new NullPointerException("O id informado nao consta no sistema");
	}
	public void removerMensagem(Mensagem mensagem) throws MensagemInexistenteException {
		cadastroMensagem.removerMensagem(mensagem);
	}
	public void removerMensagemId(long id) throws MensagemInexistenteException {
		cadastroMensagem.removerMensagemId(id);
	}
	public void removerMensagem(String frase) {
		cadastroMensagem.removerMensagem(frase);
	}
	
	// Forum - BUSCA
	public Forum localizarForumId(long id) {
		return cadastroForum.localizarForumId(id);
	}
	public Topico localizarTopicoId(long id) {
		return cadastroTopico.localizarTopicoId(id);
	}
	public Mensagem localizarMensagemId(long id) {
		return cadastroMensagem.localizarMensagemId(id);
	}
	// busca por parametro
	public List<Topico> procurarTopico(String busca) throws TopicoInexistenteException {
		return cadastroTopico.procurarTopicoTitulo(busca);	
	}
}
package com.runapp.negocio.fachada;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.negocio.basica.livros.Livro;
import com.runapp.negocio.basica.pedidos.ItemPedido;
import com.runapp.negocio.basica.pedidos.Pedido;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.basica.usuarios.Usuario;
import com.runapp.negocio.cadastro.InterfaceCadastroPedido;
import com.runapp.negocio.cadastro.InterfaceCadastroUsuario;
import com.runapp.negocio.cadastro.exception.*;
import com.runapp.negocio.fachada.exception.NaoEhClienteException;
import com.runapp.negocio.cadastro.InterfaceCadastroLivro;

@Service
public class Fachada {
	@Autowired
	private InterfaceCadastroUsuario cadastroUsuario;
	@Autowired
	private InterfaceCadastroPedido cadastroPedido;
	@Autowired
	private InterfaceCadastroLivro cadastroLivro;
	
	// Usuario
	public Usuario procurarUsuarioEmail(String email) throws UsuarioNaoExisteException {
		return cadastroUsuario.procurarUsuarioEmail(email);
	}
	public Usuario adicionarUsuario(Usuario usuario) throws UsuarioDuplicadoException {
		return cadastroUsuario.adicionarUsuario(usuario);
	}
	public List<Usuario> listarUsuarios() {
		return cadastroUsuario.listarUsuarios();
	}
	public Usuario procurarUsuarioId(Long id) {
		return cadastroUsuario.procurarUsuarioId(id);
	}
	public boolean existeUsuarioId(Long id) {
		return cadastroUsuario.existeUsuarioId(id);
	}
	public void salvarAlteracaoUsuario(Usuario usuario) throws UsuarioNaoExisteException, UsuarioDuplicadoException {
		cadastroUsuario.salvarAlteracaoUsuario(usuario);
	}
	
	// Pedido
	public List<Pedido> exibirHistoricoCliente(Cliente cliente) {
		return cadastroPedido.exibirHistoricoCliente(cliente);
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
	public void cancelarPedidoCliente(String emailCliente) throws UsuarioNaoExisteException, NaoEhClienteException, UsuarioDuplicadoException {
		Usuario u = procurarUsuarioEmail(emailCliente);
		if (!(u instanceof Cliente)) throw new NaoEhClienteException(emailCliente);
		Cliente cliente = (Cliente) u;
		
		Pedido pedido = cliente.getPedidoPendente();
		removerPedido(pedido);
		
		cliente.setPedidoPendente(new Pedido(cliente, "PENDENTE"));
		salvarAlteracaoUsuario(cliente);
	}
	public void finalizarPedidoCliente(String emailCliente) throws UsuarioNaoExisteException, NaoEhClienteException, UsuarioDuplicadoException {
		Usuario u = procurarUsuarioEmail(emailCliente);
		if (!(u instanceof Cliente)) throw new NaoEhClienteException(emailCliente);
		Cliente cliente = (Cliente) u;
		
		Pedido pedido = cliente.getPedidoPendente();
		pedido.setStatus("FINALIZADO");
		salvarPedido(pedido);
		
		cliente.setPedidoPendente(new Pedido(cliente, "PENDENTE"));
		salvarAlteracaoUsuario(cliente);
	}
	
	// Negocio Livro-Pedido-Cliente
	public void adicionarLivroPedidoCliente(Livro livro, int quantidade, boolean isEbook, String emailCliente) throws UsuarioNaoExisteException, NaoEhClienteException {
		Usuario u = procurarUsuarioEmail(emailCliente);
		if (!(u instanceof Cliente)) throw new NaoEhClienteException(emailCliente);
		Cliente cliente = (Cliente) u;
		
		// ajustar para receber o id do livro e verificar se está no repositorio
		ItemPedido item = new ItemPedido(livro, quantidade, isEbook);
		
		Pedido pedido = cliente.getPedidoPendente();
		pedido.adicionarItem(item);
		salvarPedido(pedido);
	}
}

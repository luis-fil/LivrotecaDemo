package com.runapp.comunicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runapp.negocio.basica.pedidos.ItemPedido;
import com.runapp.negocio.basica.pedidos.Pedido;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;
import com.runapp.negocio.cadastro.exception.QuantidadeInsuficienteException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.Fachada;
import com.runapp.negocio.fachada.exception.ClienteNaoExisteException;
import com.runapp.negocio.fachada.exception.PedidoVazioException;
import com.runapp.negocio.fachada.exception.QuantidadeInvalidaException;

@RestController
@RequestMapping("/api")
public class PedidoController {
	@Autowired
	public Fachada fachada;
	
	@GetMapping("/cliente/{id}/pedido")
	public Pedido exibirPedidoCliente(@PathVariable long id) throws UsuarioNaoExisteException, ClienteNaoExisteException {
		Cliente c = fachada.procurarClienteId(id);
		return c.getPedidoPendente();
	}
	
	@PatchMapping("/cliente/{id}/pedido")
	public String adicionarLivroPedidoCliente(@PathVariable long id, @RequestBody ItemPedido item) throws UsuarioNaoExisteException, ClienteNaoExisteException, LivroNaoExisteException, QuantidadeInvalidaException, QuantidadeInsuficienteException {
		fachada.adicionarLivroPedidoCliente(item.getLivro().getId(), item.getQuantidade(), item.isEbook(), id);
		return "Livro adicionado ao pedido com sucesso!";
	}
	
	@DeleteMapping("/cliente/{id}/pedido")
	public String cancelarPedidoCliente(@PathVariable long id) throws UsuarioNaoExisteException, ClienteNaoExisteException, UsuarioDuplicadoException {
		fachada.cancelarPedidoCliente(id);
		return "Pedido cancelado!";
	}
	
	@PostMapping
	public String finalizarCompraCliente(@PathVariable long id) throws UsuarioNaoExisteException, ClienteNaoExisteException, UsuarioDuplicadoException, LivroNaoExisteException, QuantidadeInsuficienteException, PedidoVazioException {
		double valorTotal = fachada.finalizarPedidoCliente(id);
		return String.format("Compra finalizada com sucesso! Valor total: R$%.2f", valorTotal);
	}
	
	@GetMapping("/cliente/{id}/pedido/historico")
	public List<Pedido> exibirHistoricoCliente(@PathVariable long id) throws UsuarioNaoExisteException, ClienteNaoExisteException {
		Cliente c = fachada.procurarClienteId(id);
		return fachada.exibirHistoricoCliente(c.getId());
	}
	
	@GetMapping("/admin/pedido")
	public List<Pedido> exibirExtrato() {
		return fachada.exibirExtrato();
	}
}

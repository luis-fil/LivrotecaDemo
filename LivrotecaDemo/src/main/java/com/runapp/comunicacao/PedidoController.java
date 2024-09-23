package com.runapp.comunicacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runapp.comunicacao.dto.RequisicaoAdicionarLivroPedido;
import com.runapp.negocio.basica.pedidos.Pedido;
import com.runapp.negocio.basica.usuarios.Cliente;
import com.runapp.negocio.cadastro.exception.LivroNaoExisteException;
import com.runapp.negocio.cadastro.exception.QuantidadeInsuficienteException;
import com.runapp.negocio.cadastro.exception.TipoDiferenteUsuarioException;
import com.runapp.negocio.cadastro.exception.UsuarioDuplicadoException;
import com.runapp.negocio.cadastro.exception.UsuarioNaoExisteException;
import com.runapp.negocio.fachada.Fachada;
import com.runapp.negocio.fachada.exception.ClienteNaoExisteException;
import com.runapp.negocio.fachada.exception.PedidoVazioException;
import com.runapp.negocio.fachada.exception.QuantidadeInvalidaException;

/**
 * Essa classe é o controlador dos endpoints relacionados aos pedidos
 * @author José Matheus
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class PedidoController {
	@Autowired
	public Fachada fachada;
	
	@GetMapping("/cliente/{id}/pedido")
	public ResponseEntity<?> exibirPedidoCliente(@PathVariable long id) {
		try {
			Cliente c = fachada.procurarClienteId(id);
			return ResponseEntity.ok(c.getPedidoPendente());
		} catch (UsuarioNaoExisteException | ClienteNaoExisteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/cliente/{id}/pedido")
	public ResponseEntity<String> adicionarLivroPedidoCliente(@PathVariable long id, @RequestBody RequisicaoAdicionarLivroPedido livro) {
		try {
			fachada.adicionarLivroPedidoCliente(livro.getIdLivro(), livro.getQuantidade(), livro.isEbook(), id);
			return ResponseEntity.ok("Livro adicionado ao pedido com sucesso!");
		} catch (UsuarioNaoExisteException | ClienteNaoExisteException | LivroNaoExisteException | QuantidadeInvalidaException | QuantidadeInsuficienteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/cliente/{id}/pedido")
	public ResponseEntity<String> cancelarPedidoCliente(@PathVariable long id) {
		try {
			fachada.cancelarPedidoCliente(id);
			return ResponseEntity.ok("Pedido cancelado!");
		} catch (UsuarioNaoExisteException | ClienteNaoExisteException | UsuarioDuplicadoException | TipoDiferenteUsuarioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/cliente/{id}/pedido")
	public ResponseEntity<String> finalizarCompraCliente(@PathVariable long id) {
		try {
			double valorTotal = fachada.finalizarPedidoCliente(id);
			return ResponseEntity.ok(String.format("Compra finalizada com sucesso! Valor total: R$%.2f", valorTotal));
		} catch (UsuarioNaoExisteException | ClienteNaoExisteException | UsuarioDuplicadoException | LivroNaoExisteException | QuantidadeInsuficienteException | PedidoVazioException | TipoDiferenteUsuarioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/cliente/{id}/pedido/historico")
	public ResponseEntity<?> exibirHistoricoCliente(@PathVariable long id) {
		try {
			Cliente c = fachada.procurarClienteId(id);
			return ResponseEntity.ok(fachada.exibirHistoricoCliente(c.getId()));
		} catch (UsuarioNaoExisteException | ClienteNaoExisteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/admin/pedido/extrato")
	public ResponseEntity<List<Pedido>> exibirExtrato() {
		return ResponseEntity.ok(fachada.exibirExtrato());
	}
}

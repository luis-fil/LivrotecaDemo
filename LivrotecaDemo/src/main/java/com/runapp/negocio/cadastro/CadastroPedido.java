package com.runapp.negocio.cadastro;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioPedido;
import com.runapp.negocio.basica.pedidos.Pedido;
import com.runapp.negocio.basica.usuarios.Cliente;

@Service
public class CadastroPedido implements InterfaceCadastroPedido {
	@Autowired
	private InterfaceRepositorioPedido repositorioPedido;
	
	@Override
	public List<Pedido> exibirHistoricoCliente(Cliente cliente) {
		return repositorioPedido.findByCliente_IdAndStatusContaining(cliente.getId(), "FINALIZADO");
	}

	@Override
	public List<Pedido> exibirExtrato() {
		return repositorioPedido.findByStatusContaining("FINALIZADO");
	}

	@Override
	public Pedido salvarPedido(Pedido pedido) {
		return repositorioPedido.save(pedido);
	}

	@Override
	public Pedido procurarPedidoId(Long id) {
		return repositorioPedido.findById(id).orElse(null);
	}

	@Override
	public boolean existePedidoId(Long id) {
		return repositorioPedido.existsById(id);
	}

	@Override
	public void removerPedido(Pedido pedido) {
		pedido.setStatus("CANCELADO");
		repositorioPedido.delete(pedido);
	}
}

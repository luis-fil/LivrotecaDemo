package com.runapp.negocio.cadastro;

import java.util.List;

import com.runapp.negocio.basica.pedidos.Pedido;

public interface InterfaceCadastroPedido {

	List<Pedido> exibirHistoricoCliente(Long idCliente);

	List<Pedido> exibirExtrato();

	Pedido salvarPedido(Pedido pedido);

	Pedido procurarPedidoId(Long id);

	boolean existePedidoId(Long id);

	void removerPedido(Pedido pedido);

}
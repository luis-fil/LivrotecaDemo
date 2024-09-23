package com.runapp.negocio.cadastro;

import java.util.List;

import com.runapp.negocio.basica.pedidos.Pedido;

/**
 * @author José Matheus
 * @version 1.0
 */
public interface InterfaceCadastroPedido {

	List<Pedido> exibirHistoricoCliente(Long idCliente);

	List<Pedido> exibirExtrato();

	Pedido salvarPedido(Pedido pedido);

	Pedido procurarPedidoId(Long id);

	boolean existePedidoId(Long id);

	void removerPedido(Pedido pedido);

}
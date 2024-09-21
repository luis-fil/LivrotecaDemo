package com.runapp.negocio.cadastro;

import java.util.List;

import com.runapp.negocio.basica.pedidos.Pedido;
import com.runapp.negocio.basica.usuarios.Cliente;

public interface InterfaceCadastroPedido {

	List<Pedido> exibirHistoricoCliente(Cliente cliente);

	List<Pedido> exibirExtrato();

	Pedido salvarPedido(Pedido pedido);

	Pedido procurarPedidoId(Long id);

	boolean existePedidoId(Long id);

	void removerPedido(Pedido pedido);

}
package com.runapp.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioPedido;
import com.runapp.negocio.basica.pedidos.Pedido;
import com.runapp.negocio.basica.usuarios.Cliente;

@Service
public class CadastroPedido {
	@Autowired
	private InterfaceRepositorioPedido repositorioPedido;
	
	public List<Pedido> exibirHistoricoCliente(Cliente cliente) {
		return repositorioPedido.findByCliente_IdAndStatusContaining(cliente.getId(), "FINALIZADO");
	}

	public List<Pedido> exibirExtrato(String status) {
		return repositorioPedido.findByStatusContaining(status);
	}

	public Pedido save(Pedido pedido) {
		return repositorioPedido.save(pedido);
	}

	public Optional<Pedido> findById(Long id) {
		return repositorioPedido.findById(id);
	}

	public boolean existsById(Long id) {
		return repositorioPedido.existsById(id);
	}

	public void delete(Pedido entity) {
		repositorioPedido.delete(entity);
	}
}

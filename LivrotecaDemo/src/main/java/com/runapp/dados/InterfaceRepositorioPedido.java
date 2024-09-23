package com.runapp.dados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runapp.negocio.basica.pedidos.Pedido;

/**
 * @author José Matheus
 * @version 1.0
 */
@Repository
public interface InterfaceRepositorioPedido extends JpaRepository<Pedido, Long> {
	
	public List<Pedido> findByCliente_IdAndStatus(Long idCliente, String status);
	
	public List<Pedido> findByStatus(String status);
	
}

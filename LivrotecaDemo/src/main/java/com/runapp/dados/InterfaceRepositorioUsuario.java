package com.runapp.dados;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runapp.negocio.basica.usuarios.Usuario;

/**
 * @author José Matheus
 * @version 1.0
 */
@Repository
public interface InterfaceRepositorioUsuario extends JpaRepository<Usuario, Long> {
	public Usuario findByEmail(String email);
}

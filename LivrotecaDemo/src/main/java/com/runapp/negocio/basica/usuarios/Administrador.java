package com.runapp.negocio.basica.usuarios;

import jakarta.persistence.Entity;

/**
 * Essa classe representa um administrador do sistema
 * @author José Matheus
 * @version 1.0
 */

@Entity
public class Administrador extends Usuario{
	public Administrador() {
		super();
	}
	public Administrador(String nome, String email, String senha) {
		super(nome, email, senha);
	}
}

package com.runapp.negocio.basica.usuarios;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario{
	public Administrador() {
		super();
	}
	public Administrador(String email, String senha, String nome) {
		super(email, senha, nome);
	}
}

package com.runapp.negocio.basica.usuarios;

import jakarta.persistence.Entity;

@Entity
public class Administrador extends Usuario{
	public Administrador() {
		super();
	}
	public Administrador(String nome, String email, String senha) {
		super(nome, email, senha);
	}
}

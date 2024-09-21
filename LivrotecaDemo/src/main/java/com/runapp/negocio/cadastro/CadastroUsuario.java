package com.runapp.negocio.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioUsuario;

@Service
public class CadastroUsuario {
	@Autowired
	private InterfaceRepositorioUsuario repositorioUsuario;
}

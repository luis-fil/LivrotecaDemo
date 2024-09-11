package com.runapp.negocio.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runapp.dados.InterfaceRepositorioForum;

@Service
public class CadastroForum {
	@Autowired
	private InterfaceRepositorioForum colecaoForum;
	
	
}

package com.runapp.dados;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runapp.negocio.basica.foruns.Mensagem;

@Repository
public interface InterfaceRepositorioMensagem extends JpaRepository<Mensagem, Long>{
	Optional<Mensagem> findById(long id);
}

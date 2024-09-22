package com.runapp.dados;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runapp.negocio.basica.foruns.Mensagem;
import com.runapp.negocio.basica.foruns.Topico;
/**
 * @author Luis Filipe
 * @version 1.00
 */
@Repository
public interface InterfaceRepositorioMensagem extends JpaRepository<Mensagem, Long>{
	Optional<Mensagem> findById(long id);
	List<Mensagem> findByTopico(Topico t);
	void deleteAllByFraseContaining(String frase);
}

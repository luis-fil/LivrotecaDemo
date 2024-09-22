package com.runapp.dados;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runapp.negocio.basica.foruns.Forum;
import com.runapp.negocio.basica.foruns.Topico;
/**
 * @author Luis Filipe
 * @version 1.00
 */
@Repository
public interface InterfaceRepositorioTopico extends JpaRepository<Topico, Long>{
	Optional<Topico> findById(long id);
	List<Topico> findByTituloContainingIgnoreCase(String titulo);
	List<Topico> findByForum(Forum f);
}

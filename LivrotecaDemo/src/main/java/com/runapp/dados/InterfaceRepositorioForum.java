package com.runapp.dados;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runapp.negocio.basica.foruns.Forum;

/**
 * @author Luis Filipe
 * @version 1.00
 */
@Repository
public interface InterfaceRepositorioForum extends JpaRepository<Forum, Long>{
	Optional<Forum> findById(long id);
	Forum findByTitulo(String titulo);
}

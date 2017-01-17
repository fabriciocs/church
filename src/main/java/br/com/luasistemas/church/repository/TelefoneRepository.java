package br.com.luasistemas.church.repository;

import br.com.luasistemas.church.domain.Telefone;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Telefone entity.
 */
@SuppressWarnings("unused")
public interface TelefoneRepository extends JpaRepository<Telefone,Long> {

}

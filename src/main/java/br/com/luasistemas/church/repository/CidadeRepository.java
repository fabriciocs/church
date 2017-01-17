package br.com.luasistemas.church.repository;

import br.com.luasistemas.church.domain.Cidade;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Cidade entity.
 */
@SuppressWarnings("unused")
public interface CidadeRepository extends JpaRepository<Cidade,Long> {

}

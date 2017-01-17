package br.com.luasistemas.church.repository;

import br.com.luasistemas.church.domain.Endereco;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Endereco entity.
 */
@SuppressWarnings("unused")
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

    @Query("select distinct endereco from Endereco endereco left join fetch endereco.telefones")
    List<Endereco> findAllWithEagerRelationships();

    @Query("select endereco from Endereco endereco left join fetch endereco.telefones where endereco.id =:id")
    Endereco findOneWithEagerRelationships(@Param("id") Long id);

}

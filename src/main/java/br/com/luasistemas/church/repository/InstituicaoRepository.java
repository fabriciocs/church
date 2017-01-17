package br.com.luasistemas.church.repository;

import br.com.luasistemas.church.domain.Instituicao;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Instituicao entity.
 */
@SuppressWarnings("unused")
public interface InstituicaoRepository extends JpaRepository<Instituicao,Long> {

    @Query("select distinct instituicao from Instituicao instituicao left join fetch instituicao.enderecos left join fetch instituicao.telefones")
    List<Instituicao> findAllWithEagerRelationships();

    @Query("select instituicao from Instituicao instituicao left join fetch instituicao.enderecos left join fetch instituicao.telefones where instituicao.id =:id")
    Instituicao findOneWithEagerRelationships(@Param("id") Long id);

}

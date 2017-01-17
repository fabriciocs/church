package br.com.luasistemas.church.service;

import br.com.luasistemas.church.domain.Instituicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Instituicao.
 */
public interface InstituicaoService {

    /**
     * Save a instituicao.
     *
     * @param instituicao the entity to save
     * @return the persisted entity
     */
    Instituicao save(Instituicao instituicao);

    /**
     *  Get all the instituicaos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Instituicao> findAll(Pageable pageable);

    /**
     *  Get the "id" instituicao.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Instituicao findOne(Long id);

    /**
     *  Delete the "id" instituicao.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

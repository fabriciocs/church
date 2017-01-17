package br.com.luasistemas.church.service;

import br.com.luasistemas.church.domain.Telefone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Telefone.
 */
public interface TelefoneService {

    /**
     * Save a telefone.
     *
     * @param telefone the entity to save
     * @return the persisted entity
     */
    Telefone save(Telefone telefone);

    /**
     *  Get all the telefones.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Telefone> findAll(Pageable pageable);

    /**
     *  Get the "id" telefone.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Telefone findOne(Long id);

    /**
     *  Delete the "id" telefone.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

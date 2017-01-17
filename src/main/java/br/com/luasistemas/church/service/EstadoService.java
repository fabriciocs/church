package br.com.luasistemas.church.service;

import br.com.luasistemas.church.domain.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Estado.
 */
public interface EstadoService {

    /**
     * Save a estado.
     *
     * @param estado the entity to save
     * @return the persisted entity
     */
    Estado save(Estado estado);

    /**
     *  Get all the estados.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Estado> findAll(Pageable pageable);

    /**
     *  Get the "id" estado.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Estado findOne(Long id);

    /**
     *  Delete the "id" estado.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

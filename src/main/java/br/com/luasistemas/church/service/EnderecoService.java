package br.com.luasistemas.church.service;

import br.com.luasistemas.church.domain.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Endereco.
 */
public interface EnderecoService {

    /**
     * Save a endereco.
     *
     * @param endereco the entity to save
     * @return the persisted entity
     */
    Endereco save(Endereco endereco);

    /**
     *  Get all the enderecos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Endereco> findAll(Pageable pageable);

    /**
     *  Get the "id" endereco.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Endereco findOne(Long id);

    /**
     *  Delete the "id" endereco.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}

package br.com.luasistemas.church.service.impl;

import br.com.luasistemas.church.service.EstadoService;
import br.com.luasistemas.church.domain.Estado;
import br.com.luasistemas.church.repository.EstadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Estado.
 */
@Service
@Transactional
public class EstadoServiceImpl implements EstadoService{

    private final Logger log = LoggerFactory.getLogger(EstadoServiceImpl.class);
    
    @Inject
    private EstadoRepository estadoRepository;

    /**
     * Save a estado.
     *
     * @param estado the entity to save
     * @return the persisted entity
     */
    public Estado save(Estado estado) {
        log.debug("Request to save Estado : {}", estado);
        Estado result = estadoRepository.save(estado);
        return result;
    }

    /**
     *  Get all the estados.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Estado> findAll(Pageable pageable) {
        log.debug("Request to get all Estados");
        Page<Estado> result = estadoRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one estado by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Estado findOne(Long id) {
        log.debug("Request to get Estado : {}", id);
        Estado estado = estadoRepository.findOne(id);
        return estado;
    }

    /**
     *  Delete the  estado by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Estado : {}", id);
        estadoRepository.delete(id);
    }
}

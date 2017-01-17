package br.com.luasistemas.church.service.impl;

import br.com.luasistemas.church.service.TelefoneService;
import br.com.luasistemas.church.domain.Telefone;
import br.com.luasistemas.church.repository.TelefoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Telefone.
 */
@Service
@Transactional
public class TelefoneServiceImpl implements TelefoneService{

    private final Logger log = LoggerFactory.getLogger(TelefoneServiceImpl.class);
    
    @Inject
    private TelefoneRepository telefoneRepository;

    /**
     * Save a telefone.
     *
     * @param telefone the entity to save
     * @return the persisted entity
     */
    public Telefone save(Telefone telefone) {
        log.debug("Request to save Telefone : {}", telefone);
        Telefone result = telefoneRepository.save(telefone);
        return result;
    }

    /**
     *  Get all the telefones.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Telefone> findAll(Pageable pageable) {
        log.debug("Request to get all Telefones");
        Page<Telefone> result = telefoneRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one telefone by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Telefone findOne(Long id) {
        log.debug("Request to get Telefone : {}", id);
        Telefone telefone = telefoneRepository.findOne(id);
        return telefone;
    }

    /**
     *  Delete the  telefone by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Telefone : {}", id);
        telefoneRepository.delete(id);
    }
}

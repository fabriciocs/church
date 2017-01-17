package br.com.luasistemas.church.service.impl;

import br.com.luasistemas.church.service.EnderecoService;
import br.com.luasistemas.church.domain.Endereco;
import br.com.luasistemas.church.repository.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Endereco.
 */
@Service
@Transactional
public class EnderecoServiceImpl implements EnderecoService{

    private final Logger log = LoggerFactory.getLogger(EnderecoServiceImpl.class);
    
    @Inject
    private EnderecoRepository enderecoRepository;

    /**
     * Save a endereco.
     *
     * @param endereco the entity to save
     * @return the persisted entity
     */
    public Endereco save(Endereco endereco) {
        log.debug("Request to save Endereco : {}", endereco);
        Endereco result = enderecoRepository.save(endereco);
        return result;
    }

    /**
     *  Get all the enderecos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Endereco> findAll(Pageable pageable) {
        log.debug("Request to get all Enderecos");
        Page<Endereco> result = enderecoRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one endereco by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Endereco findOne(Long id) {
        log.debug("Request to get Endereco : {}", id);
        Endereco endereco = enderecoRepository.findOneWithEagerRelationships(id);
        return endereco;
    }

    /**
     *  Delete the  endereco by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Endereco : {}", id);
        enderecoRepository.delete(id);
    }
}

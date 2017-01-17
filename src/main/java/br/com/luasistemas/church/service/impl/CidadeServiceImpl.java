package br.com.luasistemas.church.service.impl;

import br.com.luasistemas.church.service.CidadeService;
import br.com.luasistemas.church.domain.Cidade;
import br.com.luasistemas.church.repository.CidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Cidade.
 */
@Service
@Transactional
public class CidadeServiceImpl implements CidadeService{

    private final Logger log = LoggerFactory.getLogger(CidadeServiceImpl.class);
    
    @Inject
    private CidadeRepository cidadeRepository;

    /**
     * Save a cidade.
     *
     * @param cidade the entity to save
     * @return the persisted entity
     */
    public Cidade save(Cidade cidade) {
        log.debug("Request to save Cidade : {}", cidade);
        Cidade result = cidadeRepository.save(cidade);
        return result;
    }

    /**
     *  Get all the cidades.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Cidade> findAll(Pageable pageable) {
        log.debug("Request to get all Cidades");
        Page<Cidade> result = cidadeRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one cidade by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Cidade findOne(Long id) {
        log.debug("Request to get Cidade : {}", id);
        Cidade cidade = cidadeRepository.findOne(id);
        return cidade;
    }

    /**
     *  Delete the  cidade by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Cidade : {}", id);
        cidadeRepository.delete(id);
    }
}

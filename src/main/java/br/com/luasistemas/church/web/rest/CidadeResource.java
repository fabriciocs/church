package br.com.luasistemas.church.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.luasistemas.church.domain.Cidade;
import br.com.luasistemas.church.service.CidadeService;
import br.com.luasistemas.church.web.rest.util.HeaderUtil;
import br.com.luasistemas.church.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Cidade.
 */
@RestController
@RequestMapping("/api")
public class CidadeResource {

    private final Logger log = LoggerFactory.getLogger(CidadeResource.class);
        
    @Inject
    private CidadeService cidadeService;

    /**
     * POST  /cidades : Create a new cidade.
     *
     * @param cidade the cidade to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cidade, or with status 400 (Bad Request) if the cidade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cidades")
    @Timed
    public ResponseEntity<Cidade> createCidade(@Valid @RequestBody Cidade cidade) throws URISyntaxException {
        log.debug("REST request to save Cidade : {}", cidade);
        if (cidade.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("cidade", "idexists", "A new cidade cannot already have an ID")).body(null);
        }
        Cidade result = cidadeService.save(cidade);
        return ResponseEntity.created(new URI("/api/cidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("cidade", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cidades : Updates an existing cidade.
     *
     * @param cidade the cidade to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cidade,
     * or with status 400 (Bad Request) if the cidade is not valid,
     * or with status 500 (Internal Server Error) if the cidade couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cidades")
    @Timed
    public ResponseEntity<Cidade> updateCidade(@Valid @RequestBody Cidade cidade) throws URISyntaxException {
        log.debug("REST request to update Cidade : {}", cidade);
        if (cidade.getId() == null) {
            return createCidade(cidade);
        }
        Cidade result = cidadeService.save(cidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("cidade", cidade.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cidades : get all the cidades.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cidades in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/cidades")
    @Timed
    public ResponseEntity<List<Cidade>> getAllCidades(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Cidades");
        Page<Cidade> page = cidadeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cidades");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cidades/:id : get the "id" cidade.
     *
     * @param id the id of the cidade to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cidade, or with status 404 (Not Found)
     */
    @GetMapping("/cidades/{id}")
    @Timed
    public ResponseEntity<Cidade> getCidade(@PathVariable Long id) {
        log.debug("REST request to get Cidade : {}", id);
        Cidade cidade = cidadeService.findOne(id);
        return Optional.ofNullable(cidade)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /cidades/:id : delete the "id" cidade.
     *
     * @param id the id of the cidade to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cidades/{id}")
    @Timed
    public ResponseEntity<Void> deleteCidade(@PathVariable Long id) {
        log.debug("REST request to delete Cidade : {}", id);
        cidadeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cidade", id.toString())).build();
    }

}

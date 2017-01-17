package br.com.luasistemas.church.web.rest;

import br.com.luasistemas.church.ChurchApp;

import br.com.luasistemas.church.domain.Instituicao;
import br.com.luasistemas.church.domain.Endereco;
import br.com.luasistemas.church.repository.InstituicaoRepository;
import br.com.luasistemas.church.service.InstituicaoService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InstituicaoResource REST controller.
 *
 * @see InstituicaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChurchApp.class)
public class InstituicaoResourceIntTest {

    private static final String DEFAULT_CNPJ = "AAAAAAAAAAAAAA";
    private static final String UPDATED_CNPJ = "BBBBBBBBBBBBBB";

    private static final String DEFAULT_INSCRICAO_ESTADUAL = "AAAAAAAAAA";
    private static final String UPDATED_INSCRICAO_ESTADUAL = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_EMPRESARIAL = "AAAAAAAAAA";
    private static final String UPDATED_NOME_EMPRESARIAL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_CADASTRO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_CADASTRO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBSERVACAO = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACAO = "BBBBBBBBBB";

    @Inject
    private InstituicaoRepository instituicaoRepository;

    @Inject
    private InstituicaoService instituicaoService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restInstituicaoMockMvc;

    private Instituicao instituicao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InstituicaoResource instituicaoResource = new InstituicaoResource();
        ReflectionTestUtils.setField(instituicaoResource, "instituicaoService", instituicaoService);
        this.restInstituicaoMockMvc = MockMvcBuilders.standaloneSetup(instituicaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Instituicao createEntity(EntityManager em) {
        Instituicao instituicao = new Instituicao()
                .cnpj(DEFAULT_CNPJ)
                .inscricaoEstadual(DEFAULT_INSCRICAO_ESTADUAL)
                .nomeEmpresarial(DEFAULT_NOME_EMPRESARIAL)
                .dataCadastro(DEFAULT_DATA_CADASTRO)
                .observacao(DEFAULT_OBSERVACAO);
        // Add required entity
        Endereco enderecos = EnderecoResourceIntTest.createEntity(em);
        em.persist(enderecos);
        em.flush();
        instituicao.getEnderecos().add(enderecos);
        return instituicao;
    }

    @Before
    public void initTest() {
        instituicao = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstituicao() throws Exception {
        int databaseSizeBeforeCreate = instituicaoRepository.findAll().size();

        // Create the Instituicao

        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isCreated());

        // Validate the Instituicao in the database
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeCreate + 1);
        Instituicao testInstituicao = instituicaoList.get(instituicaoList.size() - 1);
        assertThat(testInstituicao.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testInstituicao.getInscricaoEstadual()).isEqualTo(DEFAULT_INSCRICAO_ESTADUAL);
        assertThat(testInstituicao.getNomeEmpresarial()).isEqualTo(DEFAULT_NOME_EMPRESARIAL);
        assertThat(testInstituicao.getDataCadastro()).isEqualTo(DEFAULT_DATA_CADASTRO);
        assertThat(testInstituicao.getObservacao()).isEqualTo(DEFAULT_OBSERVACAO);
    }

    @Test
    @Transactional
    public void createInstituicaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = instituicaoRepository.findAll().size();

        // Create the Instituicao with an existing ID
        Instituicao existingInstituicao = new Instituicao();
        existingInstituicao.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingInstituicao)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCnpjIsRequired() throws Exception {
        int databaseSizeBeforeTest = instituicaoRepository.findAll().size();
        // set the field null
        instituicao.setCnpj(null);

        // Create the Instituicao, which fails.

        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isBadRequest());

        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInscricaoEstadualIsRequired() throws Exception {
        int databaseSizeBeforeTest = instituicaoRepository.findAll().size();
        // set the field null
        instituicao.setInscricaoEstadual(null);

        // Create the Instituicao, which fails.

        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isBadRequest());

        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeEmpresarialIsRequired() throws Exception {
        int databaseSizeBeforeTest = instituicaoRepository.findAll().size();
        // set the field null
        instituicao.setNomeEmpresarial(null);

        // Create the Instituicao, which fails.

        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isBadRequest());

        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataCadastroIsRequired() throws Exception {
        int databaseSizeBeforeTest = instituicaoRepository.findAll().size();
        // set the field null
        instituicao.setDataCadastro(null);

        // Create the Instituicao, which fails.

        restInstituicaoMockMvc.perform(post("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isBadRequest());

        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInstituicaos() throws Exception {
        // Initialize the database
        instituicaoRepository.saveAndFlush(instituicao);

        // Get all the instituicaoList
        restInstituicaoMockMvc.perform(get("/api/instituicaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(instituicao.getId().intValue())))
            .andExpect(jsonPath("$.[*].cnpj").value(hasItem(DEFAULT_CNPJ.toString())))
            .andExpect(jsonPath("$.[*].inscricaoEstadual").value(hasItem(DEFAULT_INSCRICAO_ESTADUAL.toString())))
            .andExpect(jsonPath("$.[*].nomeEmpresarial").value(hasItem(DEFAULT_NOME_EMPRESARIAL.toString())))
            .andExpect(jsonPath("$.[*].dataCadastro").value(hasItem(DEFAULT_DATA_CADASTRO.toString())))
            .andExpect(jsonPath("$.[*].observacao").value(hasItem(DEFAULT_OBSERVACAO.toString())));
    }

    @Test
    @Transactional
    public void getInstituicao() throws Exception {
        // Initialize the database
        instituicaoRepository.saveAndFlush(instituicao);

        // Get the instituicao
        restInstituicaoMockMvc.perform(get("/api/instituicaos/{id}", instituicao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(instituicao.getId().intValue()))
            .andExpect(jsonPath("$.cnpj").value(DEFAULT_CNPJ.toString()))
            .andExpect(jsonPath("$.inscricaoEstadual").value(DEFAULT_INSCRICAO_ESTADUAL.toString()))
            .andExpect(jsonPath("$.nomeEmpresarial").value(DEFAULT_NOME_EMPRESARIAL.toString()))
            .andExpect(jsonPath("$.dataCadastro").value(DEFAULT_DATA_CADASTRO.toString()))
            .andExpect(jsonPath("$.observacao").value(DEFAULT_OBSERVACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInstituicao() throws Exception {
        // Get the instituicao
        restInstituicaoMockMvc.perform(get("/api/instituicaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstituicao() throws Exception {
        // Initialize the database
        instituicaoService.save(instituicao);

        int databaseSizeBeforeUpdate = instituicaoRepository.findAll().size();

        // Update the instituicao
        Instituicao updatedInstituicao = instituicaoRepository.findOne(instituicao.getId());
        updatedInstituicao
                .cnpj(UPDATED_CNPJ)
                .inscricaoEstadual(UPDATED_INSCRICAO_ESTADUAL)
                .nomeEmpresarial(UPDATED_NOME_EMPRESARIAL)
                .dataCadastro(UPDATED_DATA_CADASTRO)
                .observacao(UPDATED_OBSERVACAO);

        restInstituicaoMockMvc.perform(put("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstituicao)))
            .andExpect(status().isOk());

        // Validate the Instituicao in the database
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeUpdate);
        Instituicao testInstituicao = instituicaoList.get(instituicaoList.size() - 1);
        assertThat(testInstituicao.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testInstituicao.getInscricaoEstadual()).isEqualTo(UPDATED_INSCRICAO_ESTADUAL);
        assertThat(testInstituicao.getNomeEmpresarial()).isEqualTo(UPDATED_NOME_EMPRESARIAL);
        assertThat(testInstituicao.getDataCadastro()).isEqualTo(UPDATED_DATA_CADASTRO);
        assertThat(testInstituicao.getObservacao()).isEqualTo(UPDATED_OBSERVACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingInstituicao() throws Exception {
        int databaseSizeBeforeUpdate = instituicaoRepository.findAll().size();

        // Create the Instituicao

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInstituicaoMockMvc.perform(put("/api/instituicaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(instituicao)))
            .andExpect(status().isCreated());

        // Validate the Instituicao in the database
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInstituicao() throws Exception {
        // Initialize the database
        instituicaoService.save(instituicao);

        int databaseSizeBeforeDelete = instituicaoRepository.findAll().size();

        // Get the instituicao
        restInstituicaoMockMvc.perform(delete("/api/instituicaos/{id}", instituicao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Instituicao> instituicaoList = instituicaoRepository.findAll();
        assertThat(instituicaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

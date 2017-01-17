package br.com.luasistemas.church.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Instituicao.
 */
@Entity
@Table(name = "instituicao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Instituicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 14, max = 20)
    @Pattern(regexp = "^[0-9]{2}\\.([0-9]{3}){2}/[0-9]{4}-[0-9]{2}$")
    @Column(name = "cnpj", length = 20, nullable = false)
    private String cnpj;

    @NotNull
    @Size(max = 255)
    @Column(name = "inscricao_estadual", length = 255, nullable = false)
    private String inscricaoEstadual;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "nome_empresarial", length = 255, nullable = false)
    private String nomeEmpresarial;

    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "observacao")
    private String observacao;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @NotNull
    @JoinTable(name = "instituicao_enderecos",
               joinColumns = @JoinColumn(name="instituicaos_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="enderecos_id", referencedColumnName="ID"))
    private Set<Endereco> enderecos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "instituicao_telefones",
               joinColumns = @JoinColumn(name="instituicaos_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="telefones_id", referencedColumnName="ID"))
    private Set<Telefone> telefones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Instituicao cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public Instituicao inscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
        return this;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeEmpresarial() {
        return nomeEmpresarial;
    }

    public Instituicao nomeEmpresarial(String nomeEmpresarial) {
        this.nomeEmpresarial = nomeEmpresarial;
        return this;
    }

    public void setNomeEmpresarial(String nomeEmpresarial) {
        this.nomeEmpresarial = nomeEmpresarial;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Instituicao dataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public Instituicao observacao(String observacao) {
        this.observacao = observacao;
        return this;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public Instituicao enderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
        return this;
    }

    public Instituicao addEnderecos(Endereco endereco) {
        enderecos.add(endereco);
        endereco.getInstituicoes().add(this);
        return this;
    }

    public Instituicao removeEnderecos(Endereco endereco) {
        enderecos.remove(endereco);
        endereco.getInstituicoes().remove(this);
        return this;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Instituicao telefones(Set<Telefone> telefones) {
        this.telefones = telefones;
        return this;
    }

    public Instituicao addTelefones(Telefone telefone) {
        telefones.add(telefone);
        telefone.getInstituicoes().add(this);
        return this;
    }

    public Instituicao removeTelefones(Telefone telefone) {
        telefones.remove(telefone);
        telefone.getInstituicoes().remove(this);
        return this;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Instituicao instituicao = (Instituicao) o;
        if (instituicao.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, instituicao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Instituicao{" +
            "id=" + id +
            ", cnpj='" + cnpj + "'" +
            ", inscricaoEstadual='" + inscricaoEstadual + "'" +
            ", nomeEmpresarial='" + nomeEmpresarial + "'" +
            ", dataCadastro='" + dataCadastro + "'" +
            ", observacao='" + observacao + "'" +
            '}';
    }
}

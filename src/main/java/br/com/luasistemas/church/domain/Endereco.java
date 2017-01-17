package br.com.luasistemas.church.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Endereco.
 */
@Entity
@Table(name = "endereco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Size(max = 255)
    @Column(name = "logradouro", length = 255)
    private String logradouro;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero", length = 20, nullable = false)
    private String numero;

    @Size(max = 255)
    @Column(name = "complemento", length = 255)
    private String complemento;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "bairro", length = 255, nullable = false)
    private String bairro;

    @NotNull
    @Size(min = 8, max = 8)
    @Pattern(regexp = "^[0-9]{2}\\.[0-9]{3}-[0-9]{3}$")
    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    @ManyToOne
    @NotNull
    private Cidade cidade;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "endereco_telefones",
               joinColumns = @JoinColumn(name="enderecos_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="telefones_id", referencedColumnName="ID"))
    private Set<Telefone> telefones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Endereco descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Endereco logradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public Endereco numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public Endereco complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public Endereco bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public Endereco cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Endereco cidade(Cidade cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Endereco telefones(Set<Telefone> telefones) {
        this.telefones = telefones;
        return this;
    }

    public Endereco addTelefones(Telefone telefone) {
        telefones.add(telefone);
        telefone.getEnderecos().add(this);
        return this;
    }

    public Endereco removeTelefones(Telefone telefone) {
        telefones.remove(telefone);
        telefone.getEnderecos().remove(this);
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
        Endereco endereco = (Endereco) o;
        if (endereco.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Endereco{" +
            "id=" + id +
            ", descricao='" + descricao + "'" +
            ", logradouro='" + logradouro + "'" +
            ", numero='" + numero + "'" +
            ", complemento='" + complemento + "'" +
            ", bairro='" + bairro + "'" +
            ", cep='" + cep + "'" +
            '}';
    }
}

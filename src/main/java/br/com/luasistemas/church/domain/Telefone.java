package br.com.luasistemas.church.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Telefone.
 */
@Entity
@Table(name = "telefone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Pattern(regexp = "^.*$")
    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @NotNull
    @Size(min = 11, max = 20)
    @Pattern(regexp = "^[+0-9]*$")
    @Column(name = "numero", length = 20, nullable = false)
    private String numero;

    @ManyToMany(mappedBy = "telefones")
    private Set<Endereco> enderecos = new HashSet<>();
    @ManyToMany(mappedBy = "telefones")
    private Set<Instituicao> instituicoes = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Telefone descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero() {
        return numero;
    }

    public Telefone numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Set<Endereco> getEnderecos(){
        return enderecos;
    }

    public Telefone setEnderecos(Set<Endereco> enderecos){
        this.enderecos = enderecos;
        return this;
    }

    public Set<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public Telefone setInstituicoes(Set<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Telefone telefone = (Telefone) o;
        if (telefone.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, telefone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Telefone{" +
            "id=" + id +
            ", descricao='" + descricao + "'" +
            ", numero='" + numero + "'" +
            '}';
    }
}

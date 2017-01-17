package br.com.luasistemas.church.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Estado.
 */
@Entity
@Table(name = "estado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4, max = 20)
    @Pattern(regexp = "^[A-zÀ-ÿ]+$")
    @Column(name = "descricao", length = 20, nullable = false)
    private String descricao;

    @NotNull
    @Size(min = 2, max = 2)
    @Pattern(regexp = "^[A-Z]{2,2}$")
    @Column(name = "sigla", length = 2, nullable = false)
    private String sigla;

    @Column(name = "codigo_ibge")
    private String codigoIbge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Estado descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public Estado sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public Estado codigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
        return this;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Estado estado = (Estado) o;
        if (estado.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, estado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Estado{" +
            "id=" + id +
            ", descricao='" + descricao + "'" +
            ", sigla='" + sigla + "'" +
            ", codigoIbge='" + codigoIbge + "'" +
            '}';
    }
}

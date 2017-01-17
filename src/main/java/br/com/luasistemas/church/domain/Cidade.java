package br.com.luasistemas.church.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Cidade.
 */
@Entity
@Table(name = "cidade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4, max = 255)
    @Pattern(regexp = "^[A-zÀ-ÿ\\s]*$")
    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "codigo_ibge")
    private String codigoIbge;

    @ManyToOne
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Cidade descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public Cidade codigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
        return this;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public Estado getEstado() {
        return estado;
    }

    public Cidade estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cidade cidade = (Cidade) o;
        if (cidade.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, cidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cidade{" +
            "id=" + id +
            ", descricao='" + descricao + "'" +
            ", codigoIbge='" + codigoIbge + "'" +
            '}';
    }
}

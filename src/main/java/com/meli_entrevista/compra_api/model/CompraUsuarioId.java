package com.meli_entrevista.compra_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompraUsuarioId implements Serializable {

    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    public CompraUsuarioId() {
    }

    public CompraUsuarioId(Integer idCompra, Integer idUsuario) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompraUsuarioId that = (CompraUsuarioId) o;
        return Objects.equals(idCompra, that.idCompra) &&
                Objects.equals(idUsuario, that.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, idUsuario);
    }
}
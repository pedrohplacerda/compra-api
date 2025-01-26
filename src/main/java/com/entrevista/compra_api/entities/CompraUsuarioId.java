package com.entrevista.compra_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CompraUsuarioId implements Serializable {
    @Column(name = "id_compra")
    private Integer idCompra;
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public CompraUsuarioId(Integer idCompra, Integer idUsuario) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
    }

    public CompraUsuarioId() {
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
}
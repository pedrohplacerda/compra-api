package com.entrevista.compra_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(schema = "entrevista", name = "compra_usuario")
public class CompraUsuario {
    @EmbeddedId
    private CompraUsuarioId id;
    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private LocalDateTime data;
    @Column(nullable = false)
    private boolean pago;

    public CompraUsuario() {
    }

    public CompraUsuario(CompraUsuarioId id, BigDecimal valor, LocalDateTime data, boolean pago) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.pago = pago;
    }

    public CompraUsuarioId getId() {
        return id;
    }

    public void setId(CompraUsuarioId id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
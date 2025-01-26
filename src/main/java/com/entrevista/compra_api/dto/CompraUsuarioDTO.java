package com.entrevista.compra_api.dto;

import com.entrevista.compra_api.entities.CompraUsuarioId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraUsuarioDTO {
    private CompraUsuarioId id;
    private BigDecimal valor;
    private LocalDateTime data;
    private boolean pago;

    public CompraUsuarioDTO() {
    }

    public CompraUsuarioDTO(CompraUsuarioId id, BigDecimal valor, LocalDateTime data, boolean pago) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.pago = pago;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public CompraUsuarioId getId() {
        return id;
    }

    public void setId(CompraUsuarioId id) {
        this.id = id;
    }
}

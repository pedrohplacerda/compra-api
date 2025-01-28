package com.interview.purchase_api.dto;

import com.interview.purchase_api.entities.UserPurchaseId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserPurchaseDTO {
    private UserPurchaseId id;
    private BigDecimal valor;
    private LocalDateTime data;
    private boolean pago;

    public UserPurchaseDTO() {
    }

    public UserPurchaseDTO(UserPurchaseId id, BigDecimal valor, LocalDateTime data, boolean pago) {
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

    public UserPurchaseId getId() {
        return id;
    }

    public void setId(UserPurchaseId id) {
        this.id = id;
    }
}

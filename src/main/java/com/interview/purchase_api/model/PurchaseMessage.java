package com.interview.purchase_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.purchase_api.enums.StatusCompraEnum;
import lombok.Data;

@Data
public class PurchaseMessage {
    @JsonProperty
    private String mensagem;
    @JsonProperty
    private Double saldoAtualizado;
    @JsonProperty
    private StatusCompraEnum statusCompra;

    public PurchaseMessage() {
    }

    public PurchaseMessage(String mensagem, Double saldoAtualizado, StatusCompraEnum statusCompra) {
        this.mensagem = mensagem;
        this.saldoAtualizado = saldoAtualizado;
        this.statusCompra = statusCompra;
    }
}

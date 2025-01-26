package com.entrevista.compra_api.model;

import com.entrevista.compra_api.enums.StatusCompraEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MensagemCompra {
    @JsonProperty
    private String mensagem;
    @JsonProperty
    private Double saldoAtualizado;
    @JsonProperty
    private StatusCompraEnum statusCompra;

    public MensagemCompra() {
    }

    public MensagemCompra(String mensagem, Double saldoAtualizado, StatusCompraEnum statusCompra) {
        this.mensagem = mensagem;
        this.saldoAtualizado = saldoAtualizado;
        this.statusCompra = statusCompra;
    }
}

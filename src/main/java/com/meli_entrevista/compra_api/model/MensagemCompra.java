package com.meli_entrevista.compra_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli_entrevista.compra_api.enums.StatusCompraEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensagemCompra {

    public MensagemCompra(String mensagem, StatusCompraEnum statusCompra, Double saldoAtualizado) {
        this.mensagem = mensagem;
        this.statusCompra = statusCompra;
        this.saldoAtualizado = saldoAtualizado;
    }

    @JsonProperty
    private String mensagem;
    @JsonProperty
    private Double saldoAtualizado;
    @JsonProperty
    private StatusCompraEnum statusCompra;
}

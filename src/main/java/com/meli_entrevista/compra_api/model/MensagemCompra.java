package com.meli_entrevista.compra_api.model;

import com.meli_entrevista.compra_api.enums.StatusCompraEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensagemCompra {
    private final String mensagem;
    private final StatusCompraEnum statusCompra;
}

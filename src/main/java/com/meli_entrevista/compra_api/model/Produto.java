package com.meli_entrevista.compra_api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Produto {
    private String nome;
    private Integer valor;
    private Long quantidade;
}

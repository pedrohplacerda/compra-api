package com.meli_entrevista.compra_api.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompraRequestBody {
    @JsonProperty
    private String produto;
    @JsonProperty
    private Integer idProduto;
    @JsonProperty
    private Integer quantidade;
}

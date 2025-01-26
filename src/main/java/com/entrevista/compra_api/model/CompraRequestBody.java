package com.entrevista.compra_api.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CompraRequestBody {
    @JsonProperty
    private Integer idProduto;
    @JsonProperty
    private Integer quantidade;
    @JsonProperty
    private Integer idUsuario;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

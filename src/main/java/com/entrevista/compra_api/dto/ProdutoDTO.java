package com.entrevista.compra_api.dto;

public class ProdutoDTO {
    private Integer id;
    private String nome;
    private Integer valor;
    private Long quantidade;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Integer id, String nome, Integer valor, Long quantidade) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}

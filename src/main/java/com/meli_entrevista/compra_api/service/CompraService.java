package com.meli_entrevista.compra_api.service;

import com.meli_entrevista.compra_api.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

@Service
public class CompraService {

    @Autowired
    private ProdutoService produtoService;

    public String comprarProduto(Integer idProduto) throws URISyntaxException, IOException, InterruptedException {
        String stringHttpResponse = produtoService.buscarProduto(idProduto);
        return "stringHttpResponse";
    }
}

package com.meli_entrevista.compra_api.service;

import com.meli_entrevista.compra_api.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

@Service
public class ProdutoService {

    @Autowired
    private HttpClient httpClient;

    public String buscarProduto(Integer id) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(String.format("localhost:8082/product?id=%s", id));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
    }
}

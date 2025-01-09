package com.meli_entrevista.compra_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli_entrevista.compra_api.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ProdutoService {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private ObjectMapper objectMapper;

    public Produto buscarProduto(Integer id) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(String.format("http://localhost:8082/product?id=%s", id));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        String responseBody = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        return objectMapper.readValue(responseBody, Produto.class);
    }
}

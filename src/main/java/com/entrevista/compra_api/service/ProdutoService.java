package com.entrevista.compra_api.service;

import com.entrevista.compra_api.dto.ProdutoDTO;
import com.entrevista.compra_api.exception.ProdutoNaoEncontradoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Service
public class ProdutoService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ProdutoService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public ProdutoDTO buscarProduto(Integer id) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(String.format("http://localhost:8082/product?id=%s", id));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        String responseBody = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        ProdutoDTO produtoDTO = objectMapper.readValue(responseBody, ProdutoDTO.class);
        if (Objects.isNull(produtoDTO)) {
            throw new ProdutoNaoEncontradoException();
        }
        return produtoDTO;
    }
}

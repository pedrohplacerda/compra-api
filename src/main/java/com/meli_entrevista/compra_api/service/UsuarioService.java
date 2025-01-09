package com.meli_entrevista.compra_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli_entrevista.compra_api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class UsuarioService {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private ObjectMapper objectMapper;

    public Usuario getUsuario(Integer id) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(String.format("http://localhost:8081/users?id=%s", id));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        String responseBody = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        return objectMapper.readValue(responseBody, Usuario.class);
    }

    public Usuario atualizarUsuario(Integer id, Double saldo) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("http://localhost:8081/users");
        String requestBody = String.format("""
                {
                   "id": %s,
                   "saldo": %s
                }
                """, id, saldo);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(uri)
                .build();
        String responseBody = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        return objectMapper.readValue(responseBody, Usuario.class);
    }
}

package com.entrevista.compra_api.service;

import com.entrevista.compra_api.dto.UsuarioDTO;
import com.entrevista.compra_api.exception.UsuarioNaoEncontradoException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private ObjectMapper objectMapper;

    public UsuarioDTO getUsuario(Integer id) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(String.format("http://localhost:8081/users?id=%s", id));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        String responseBody = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        return objectMapper.readValue(responseBody, UsuarioDTO.class);
    }

    public UsuarioDTO atualizarUsuario(Integer id, Double saldo) throws URISyntaxException, IOException, InterruptedException {
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
        UsuarioDTO usuarioDTO = objectMapper.readValue(responseBody, UsuarioDTO.class);
        if (Objects.isNull(usuarioDTO)) {
            throw new UsuarioNaoEncontradoException();
        }
        return usuarioDTO;
    }
}

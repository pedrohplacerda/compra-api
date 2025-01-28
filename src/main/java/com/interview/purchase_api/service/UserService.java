package com.interview.purchase_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.purchase_api.dto.UserDTO;
import com.interview.purchase_api.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Service
public class UserService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public UserService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public UserDTO getUser(Integer id) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(String.format("http://localhost:8081/users?id=%s", id));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        String responseBody = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
        UserDTO userDTO = objectMapper.readValue(responseBody, UserDTO.class);
        if (Objects.isNull(userDTO)) {
            throw new UserNotFoundException();
        }
        return userDTO;
    }

    public UserDTO updateUser(Integer id, Double saldo) throws URISyntaxException, IOException, InterruptedException {
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
        UserDTO userDTO = objectMapper.readValue(responseBody, UserDTO.class);
        if (Objects.isNull(userDTO)) {
            throw new UserNotFoundException();
        }
        return userDTO;
    }
}

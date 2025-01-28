package com.interview.purchase_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.purchase_api.dto.ProductDTO;
import com.interview.purchase_api.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Service
public class ProductService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ProductService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public ProductDTO searchProduct(Integer id) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(String.format("http://localhost:8082/product?id=%s", id));
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        HttpResponse<String> responseBody = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        ProductDTO productDTO = objectMapper.readValue(responseBody.body(), ProductDTO.class);
        if (Objects.isNull(productDTO)) {
            throw new ProductNotFoundException();
        }
        return productDTO;
    }
}

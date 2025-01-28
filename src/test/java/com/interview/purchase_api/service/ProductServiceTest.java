package com.interview.purchase_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.purchase_api.dto.ProductDTO;
import com.interview.purchase_api.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService unit;
    @Mock
    private HttpClient httpClient;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private HttpResponse<String> mockResponse;

    @BeforeEach
    void setUp() throws IOException, InterruptedException, URISyntaxException {
        URI uri = new URI("http://localhost:8082/product?id=12");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        Mockito.when(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(mockResponse);
    }

    @Test
    @DisplayName("Tests if the HTTP GET returns the correct productDTO object.")
    void searchProduct_testCase1() throws URISyntaxException, IOException, InterruptedException {
        ProductDTO expected = new ProductDTO();
        Mockito.when(objectMapper.readValue(mockResponse.body(), ProductDTO.class)).thenReturn(expected);

        ProductDTO actual = unit.searchProduct(12);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Throws exception when product is not found.")
    void searchProduct_testCase2() throws IOException {
        Mockito.when(objectMapper.readValue(mockResponse.body(), ProductDTO.class)).thenReturn(null);

        assertThatExceptionOfType(ProductNotFoundException.class).isThrownBy(() -> unit.searchProduct(12));
    }
}
package com.interview.purchase_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.purchase_api.dto.UserDTO;
import com.interview.purchase_api.exception.UserNotFoundException;
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

import static com.interview.purchase_api.TestConstants.BALANCE;
import static com.interview.purchase_api.TestConstants.USER_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService unit;
    @Mock
    private HttpClient httpClient;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private HttpResponse<String> mockResponse;

    @Test
    @DisplayName("Sends the GET request correctly and returns an userDTO object.")
    void getUser_testCase1() throws IOException, URISyntaxException, InterruptedException {
        buildGetRequest();
        UserDTO expected = new UserDTO();
        Mockito.when(objectMapper.readValue(mockResponse.body(), UserDTO.class)).thenReturn(expected);

        UserDTO actual = unit.getUser(12);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Throws UserNotFoundException when tries to get user from id and doesn't find it.")
    void getUser_testCase2() throws IOException, URISyntaxException, InterruptedException {
        buildGetRequest();
        Mockito.when(objectMapper.readValue(mockResponse.body(), UserDTO.class)).thenReturn(null);

        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> unit.getUser(12));
    }

    @Test
    @DisplayName("Sends the GET request correctly and returns an userDTO object.")
    void updateUser_testCase1() throws IOException, URISyntaxException, InterruptedException {
        buildPutRequest();
        UserDTO expected = new UserDTO();
        Mockito.when(objectMapper.readValue(mockResponse.body(), UserDTO.class)).thenReturn(expected);

        UserDTO userDTO = unit.updateUser(12, BALANCE);

        assertEquals(expected, userDTO);
    }

    @Test
    @DisplayName("Throws UserNotFoundException when tries to get user from id and doesn't find it.")
    void updateUser_testCase2() throws IOException, URISyntaxException, InterruptedException {
        buildPutRequest();
        Mockito.when(objectMapper.readValue(mockResponse.body(), UserDTO.class)).thenReturn(null);

        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> unit.updateUser(12, BALANCE));
    }

    private void buildGetRequest() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("http://localhost:8081/users?id=12");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        Mockito.when(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(mockResponse);
    }

    private void buildPutRequest() throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("http://localhost:8081/users");
        String requestBody = String.format("""
                {
                   "id": %s,
                   "saldo": %s
                }
                """, USER_ID, BALANCE);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(uri)
                .build();
        Mockito.when(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(mockResponse);
    }
}
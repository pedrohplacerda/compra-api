package com.meli_entrevista.compra_api.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class DependecyFactory {

    @Bean
    public HttpClient buildHttpClient() {
        return HttpClient.newHttpClient();
    }
}

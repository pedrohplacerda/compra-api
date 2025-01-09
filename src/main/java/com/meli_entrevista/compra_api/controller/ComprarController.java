package com.meli_entrevista.compra_api.controller;

import com.meli_entrevista.compra_api.model.CompraRequestBody;
import com.meli_entrevista.compra_api.model.MensagemCompra;
import com.meli_entrevista.compra_api.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/comprar/produto")
public class ComprarController {

    @Autowired
    private CompraService service;

    @PostMapping
    public MensagemCompra comprar(@RequestBody CompraRequestBody compraRequestBody) throws URISyntaxException, IOException, InterruptedException {
        service.comprarProduto(compraRequestBody.getIdProduto());
        return MensagemCompra.builder().build();
    }
}

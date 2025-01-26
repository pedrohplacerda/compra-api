package com.entrevista.compra_api.controller;

import com.entrevista.compra_api.enums.StatusCompraEnum;
import com.entrevista.compra_api.model.CompraRequestBody;
import com.entrevista.compra_api.model.MensagemCompra;
import com.entrevista.compra_api.service.CompraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/comprar/produto")
public class ComprarController {

    public ComprarController(CompraService service) {
        this.service = service;
    }

    private final CompraService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity<MensagemCompra> comprar(@RequestBody CompraRequestBody requestBody) throws URISyntaxException, IOException, InterruptedException {
        MensagemCompra mensagemCompra = new MensagemCompra("Compra efetuada com sucesso!",
                service.comprarProduto(requestBody.getIdProduto(), requestBody.getIdUsuario(), requestBody.getQuantidade()),
                StatusCompraEnum.PAGAMENTO_CONCLUIDO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mensagemCompra);
    }
}

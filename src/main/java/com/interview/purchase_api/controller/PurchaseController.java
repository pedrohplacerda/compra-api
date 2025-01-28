package com.interview.purchase_api.controller;

import com.interview.purchase_api.enums.StatusCompraEnum;
import com.interview.purchase_api.model.PurchaseMessage;
import com.interview.purchase_api.model.PurchaseRequestBody;
import com.interview.purchase_api.service.BuyService;
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
public class PurchaseController {

    public PurchaseController(BuyService service) {
        this.service = service;
    }

    private final BuyService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity<PurchaseMessage> buy(@RequestBody PurchaseRequestBody requestBody) throws URISyntaxException, IOException, InterruptedException {
        PurchaseMessage purchaseMessage = new PurchaseMessage("Compra efetuada com sucesso!",
                service.buyProduct(requestBody.getIdProduto(), requestBody.getIdUsuario(), requestBody.getQuantidade()),
                StatusCompraEnum.PAGAMENTO_CONCLUIDO);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(purchaseMessage);
    }
}

package com.interview.purchase_api.handler;

import com.interview.purchase_api.exception.NotEnoughBalanceException;
import com.interview.purchase_api.exception.ProductNotFoundException;
import com.interview.purchase_api.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> produtoNaoEncontradoExceptionHandler(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<String> saldoInsuficienteExceptionHandler(NotEnoughBalanceException exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Saldo insuficiente para prosseguir com a compra.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> usuarioNaoEncontradoExceptionHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Usuário não encontrado.");
    }
}

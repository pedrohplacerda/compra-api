package com.entrevista.compra_api.handler;

import com.entrevista.compra_api.exception.ProdutoNaoEncontradoException;
import com.entrevista.compra_api.exception.SaldoInsuficienteException;
import com.entrevista.compra_api.exception.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<String> produtoNaoEncontradoExceptionHandler(ProdutoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> saldoInsuficienteExceptionHandler(SaldoInsuficienteException exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Saldo insuficiente para prosseguir com a compra.");
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> usuarioNaoEncontradoExceptionHandler(UsuarioNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Usuário não encontrado.");
    }
}

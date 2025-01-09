package com.meli_entrevista.compra_api.service;

import com.meli_entrevista.compra_api.exception.ProdutoNaoEncontradoException;
import com.meli_entrevista.compra_api.exception.SaldoInsuficienteException;
import com.meli_entrevista.compra_api.model.*;
import com.meli_entrevista.compra_api.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CompraService {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CompraUsuarioService compraUsuarioService;
    @Autowired
    private CompraRepository compraRepository;

    public Double comprarProduto(Integer idProduto, Integer idUsuario, Integer quantidade) throws URISyntaxException, IOException, InterruptedException {
        Produto produto = produtoService.buscarProduto(idProduto);
        if (Objects.isNull(produto)) {
            throw new ProdutoNaoEncontradoException();
        }
        Usuario usuario = usuarioService.getUsuario(idUsuario);
        Integer valorFinal = produto.getValor() * quantidade;
        if (usuario.getSaldo() < valorFinal) {
            throw new SaldoInsuficienteException();
        }
        Double saldoAtualizado = usuario.getSaldo() - valorFinal;
        usuarioService.atualizarUsuario(idUsuario, saldoAtualizado);
        LocalDateTime data = LocalDateTime.now();
        boolean pago = true;
        Compra compra = getCompra(data, pago);
        Compra savedCompra = compraRepository.save(compra);
        CompraUsuarioId compraUsuarioId = getCompraUsuarioId(idUsuario, savedCompra);
        CompraUsuario compraUsuario = getCompraUsuario(compraUsuarioId, valorFinal, data, pago);
        compraUsuarioService.save(compraUsuario);
        return saldoAtualizado;
    }

    private static CompraUsuario getCompraUsuario(CompraUsuarioId compraUsuarioId, Integer valorFinal, LocalDateTime data, boolean pago) {
        CompraUsuario compraUsuario = new CompraUsuario();
        compraUsuario.setId(compraUsuarioId);
        compraUsuario.setValor(BigDecimal.valueOf(valorFinal));
        compraUsuario.setData(data);
        compraUsuario.setPago(pago);
        return compraUsuario;
    }

    private static CompraUsuarioId getCompraUsuarioId(Integer idUsuario, Compra savedCompra) {
        CompraUsuarioId compraUsuarioId = new CompraUsuarioId();
        compraUsuarioId.setIdUsuario(idUsuario);
        compraUsuarioId.setIdCompra(savedCompra.getId());
        return compraUsuarioId;
    }

    private static Compra getCompra(LocalDateTime data, boolean pago) {
        Compra compra = new Compra();
        compra.setData(data);
        compra.setPago(pago);
        return compra;
    }
}

package com.meli_entrevista.compra_api.service;

import com.meli_entrevista.compra_api.exception.ProdutoNaoEncontradoException;
import com.meli_entrevista.compra_api.exception.SaldoInsuficienteException;
import com.meli_entrevista.compra_api.model.Produto;
import com.meli_entrevista.compra_api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

@Service
public class CompraService {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private UsuarioService usuarioService;

    public Double comprarProduto(Integer idProduto, Integer idUsuario, Integer quantidade) throws URISyntaxException, IOException, InterruptedException {
        Produto produto = produtoService.buscarProduto(idProduto);
        if (Objects.isNull(produto)) {
            throw new ProdutoNaoEncontradoException();
        }
        Usuario usuario = usuarioService.getUsuario(idUsuario);
        if (usuario.getSaldo() < (produto.getValor() * quantidade)) {
            throw new SaldoInsuficienteException();
        }
        Double saldoAtualizado = usuario.getSaldo() - (produto.getValor() * quantidade);
        usuarioService.atualizarUsuario(idUsuario, saldoAtualizado);
        return saldoAtualizado;
    }
}

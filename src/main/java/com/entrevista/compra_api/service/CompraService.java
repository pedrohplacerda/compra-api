package com.entrevista.compra_api.service;

import com.entrevista.compra_api.dto.CompraDTO;
import com.entrevista.compra_api.dto.CompraUsuarioDTO;
import com.entrevista.compra_api.dto.ProdutoDTO;
import com.entrevista.compra_api.dto.UsuarioDTO;
import com.entrevista.compra_api.entities.Compra;
import com.entrevista.compra_api.entities.CompraUsuarioId;
import com.entrevista.compra_api.exception.SaldoInsuficienteException;
import com.entrevista.compra_api.mapper.CompraMapper;
import com.entrevista.compra_api.repository.CompraRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
public class CompraService {

    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final CompraUsuarioService compraUsuarioService;

    public CompraService(ProdutoService produtoService, UsuarioService usuarioService, CompraUsuarioService compraUsuarioService, CompraRepository compraRepository) {
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.compraUsuarioService = compraUsuarioService;
        this.compraRepository = compraRepository;
    }

    private final CompraRepository compraRepository;

    public Double comprarProduto(Integer idProduto, Integer idUsuario, Integer quantidade) throws URISyntaxException, IOException, InterruptedException {
        LocalDateTime data = LocalDateTime.now();
        ProdutoDTO produtoDTO = produtoService.buscarProduto(idProduto);
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(idUsuario);
        Integer valorFinal = Math.multiplyExact(produtoDTO.getValor(), quantidade);
        if (usuarioDTO.getSaldo() < valorFinal) {
            throw new SaldoInsuficienteException();
        }
        Double saldoAtualizado = usuarioDTO.getSaldo() - valorFinal;
        usuarioService.atualizarUsuario(idUsuario, saldoAtualizado);
        CompraDTO compraDTO = getCompra(data);
        Compra compra = CompraMapper.INSTANCE.toCompra(compraDTO);
        Compra savedCompra = compraRepository.save(compra);
        CompraUsuarioId compraUsuarioId = getCompraUsuarioId(idUsuario, savedCompra);
        CompraUsuarioDTO compraUsuario = getCompraUsuario(compraUsuarioId, valorFinal, data);
        compraUsuarioService.save(compraUsuario);
        return saldoAtualizado;
    }

    private CompraUsuarioDTO getCompraUsuario(CompraUsuarioId compraUsuarioId, Integer valorFinal, LocalDateTime data) {
        CompraUsuarioDTO compraUsuarioDTO = new CompraUsuarioDTO();
        compraUsuarioDTO.setId(compraUsuarioId);
        compraUsuarioDTO.setValor(BigDecimal.valueOf(valorFinal));
        compraUsuarioDTO.setData(data);
        compraUsuarioDTO.setPago(Boolean.TRUE);
        return compraUsuarioDTO;
    }

    private CompraUsuarioId getCompraUsuarioId(Integer idUsuario, Compra savedCompra) {
        CompraUsuarioId compraUsuarioId = new CompraUsuarioId();
        compraUsuarioId.setIdUsuario(idUsuario);
        compraUsuarioId.setIdCompra(savedCompra.getId());
        return compraUsuarioId;
    }

    private CompraDTO getCompra(LocalDateTime data) {
        CompraDTO compraDTO = new CompraDTO();
        compraDTO.setData(data);
        compraDTO.setPago(Boolean.TRUE);
        return compraDTO;
    }
}

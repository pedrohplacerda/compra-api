package com.entrevista.compra_api.service;

import com.entrevista.compra_api.dto.CompraUsuarioDTO;
import com.entrevista.compra_api.mapper.CompraUsuarioMapper;
import com.entrevista.compra_api.repository.CompraUsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CompraUsuarioService {
    private final CompraUsuarioRepository repository;

    public void save(CompraUsuarioDTO compraUsuario) {
        repository.save(CompraUsuarioMapper.INSTANCE.toCompraUsuario(compraUsuario));
    }

    public CompraUsuarioService(CompraUsuarioRepository repository) {
        this.repository = repository;
    }
}

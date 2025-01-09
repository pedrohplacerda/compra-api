package com.meli_entrevista.compra_api.service;

import com.meli_entrevista.compra_api.model.CompraUsuario;
import com.meli_entrevista.compra_api.repository.CompraUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraUsuarioService {

    @Autowired
    private CompraUsuarioRepository repository;

    public CompraUsuario save(CompraUsuario compraUsuario) {
        return repository.save(compraUsuario);
    }
}

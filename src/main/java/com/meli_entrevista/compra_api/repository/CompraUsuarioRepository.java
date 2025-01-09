package com.meli_entrevista.compra_api.repository;

import com.meli_entrevista.compra_api.model.CompraUsuario;
import com.meli_entrevista.compra_api.model.CompraUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraUsuarioRepository extends JpaRepository<CompraUsuario, CompraUsuarioId> {
}

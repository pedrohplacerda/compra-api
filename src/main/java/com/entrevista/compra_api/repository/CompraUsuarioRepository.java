package com.entrevista.compra_api.repository;

import com.entrevista.compra_api.entities.CompraUsuario;
import com.entrevista.compra_api.entities.CompraUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraUsuarioRepository extends JpaRepository<CompraUsuario, CompraUsuarioId> {
}

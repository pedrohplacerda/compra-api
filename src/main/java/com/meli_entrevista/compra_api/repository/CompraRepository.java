package com.meli_entrevista.compra_api.repository;

import com.meli_entrevista.compra_api.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
}
